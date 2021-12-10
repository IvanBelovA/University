package ua.com.foxminded.university.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.university.dao.StudentDao;
import ua.com.foxminded.university.dao.mapper.StudentsResultExtractor;
import ua.com.foxminded.university.model.Student;

@Repository
public class StudentDaoImpl implements StudentDao{

    private static final String ALL = "SELECT * FROM students";
    private static final String BY_ID = "SELECT * FROM students WHERE id = ?";
    private static final String ADDITION = "INSERT INTO students(first_name, last_name) VALUES(?, ?)";
    private static final String DELETE = "DELETE FROM students WHERE id = ?";
    private static final String ASSIGN_COURSE = "INSERT INTO students_courses(student_id, course_id) VALUES(?, ?)";
    private static final String DELETE_COURSE = "DELETE FROM students_courses WHERE student_id = ? AND course_id = ?";
    private static final String ADD_GROUP = "UPDATE students SET group_id = ? WHERE id = ?";
    private static final String DELETE_GROUP = "UPDATE students SET group_id = ? WHERE id = ?";
    private static final String COURSES_STUDENTS = "SELECT s.id, s.first_name, s.last_name, "
            + "c.id AS course_id, c.course_name "
            + "FROM students AS s "
            + "JOIN students_courses AS sc ON sc.student_id = s.id "
            + "JOIN courses AS c ON c.id = sc.course_id "
            + "WHERE s.id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public StudentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Student> findAll() {
        return jdbcTemplate.query(ALL, new BeanPropertyRowMapper<> (Student.class));
    }

    public Student findById(int id) {
        return jdbcTemplate.query(BY_ID, new BeanPropertyRowMapper<> (Student.class), id)
                .stream().findAny().orElse(null);
    }

    public void add(Student student) {
        jdbcTemplate.update(ADDITION,
                student.getFirstName(), student.getLastName());
    }

    public void delete(Student student) {
        jdbcTemplate.update(DELETE, student.getId());
    }

    public void assignFromCourse(int studentId, int courseId) {
        jdbcTemplate.update(ASSIGN_COURSE, studentId, courseId);
    }

    public void deleteFromCourse(int studentId, int courseId) {
        jdbcTemplate.update(DELETE_COURSE,
                studentId, courseId);
    }

    public void addFromGroup(int studentId, int groupId) {
        jdbcTemplate.update(ADD_GROUP, groupId, studentId);
    }

    public void deleteFromGroup(int studentId) {
        jdbcTemplate.update(DELETE_GROUP, null, studentId);
    }

    public Student findCoursesStudent(Student student) {
        return jdbcTemplate.query(COURSES_STUDENTS, new StudentsResultExtractor(), student.getId());
    }

}
