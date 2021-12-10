package ua.com.foxminded.university.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.university.dao.CourseDao;
import ua.com.foxminded.university.dao.mapper.CourseResultExtractor;
import ua.com.foxminded.university.dao.mapper.CourseTeachersMapper;
import ua.com.foxminded.university.model.Course;

@Repository
public class CourseDaoImpl implements CourseDao{

    private static final String ALL = "SELECT * FROM courses";
    private static final String BY_ID = "SELECT * FROM courses WHERE id = ?";
    private static final String ADDITION = "INSERT INTO courses(course_name) VALUES (?)";
    private static final String DELETE = "DELETE FROM courses WHERE id = ?";
    private static final String COURSE_TEACHERS = "SELECT t.id, t.first_name, t.last_name "
            + "FROM teachers_courses AS tc "
            + "JOIN teachers AS t ON t.id = tc.student_id "
            + "WHERE tc.student_id = ?";
    private static final String COURSE_STUDENTS = "SELECT c.id AS course_id, c.course_name, "
            + "s.id, s.first_name, s.last_name "
            + "FROM students_courses AS sc "
            + "JOIN students AS s ON s.id = sc.student_id "
            + "JOIN courses AS c ON c.id = sc.course_id "
            + "WHERE c.id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CourseDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Course> findAll() {
        return jdbcTemplate.query(ALL, new BeanPropertyRowMapper<>(Course.class));
    }

    public Course findById(int id) {
        return jdbcTemplate.query(BY_ID, new BeanPropertyRowMapper<>(Course.class), id)
                .stream().findAny().orElse(null);
    }

    public void add(Course course) {
        jdbcTemplate.update(ADDITION, course.getCourseName());
    }

    public void delete(Course course) {
        jdbcTemplate.update(DELETE, course.getId());
    }

    public Course findCourseStudents(Course course) {
        return jdbcTemplate.query(COURSE_STUDENTS, new CourseResultExtractor(), course.getId());
    }

    public Course findCourseTeachers(Course course) {
        return jdbcTemplate.query(COURSE_TEACHERS, new CourseTeachersMapper(), course.getId())
                .stream().findAny().orElse(null);
    }

}
