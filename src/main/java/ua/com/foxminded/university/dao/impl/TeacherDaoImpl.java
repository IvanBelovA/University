package ua.com.foxminded.university.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.university.dao.TeacherDao;
import ua.com.foxminded.university.dao.mapper.TeacherResultExtractor;
import ua.com.foxminded.university.model.Teacher;

@Repository
public class TeacherDaoImpl implements TeacherDao{

    private static final String ALL = "SELECT * FROM teachers";
    private static final String BY_ID = "SELECT * FROM teachers WHERE id = ?";
    private static final String ADDITION = "INSERT INTO teachers(name, last_name) VALUES (?, ?)";
    private static final String DELETE = "DELETE FROM teachers WHERE id = ?";
    private static final String ADD_COURSE = "INSERT INTO teachers_courses(teacher_id, course_id) VALUES(?, ?)";
    private static final String DELETE_COURSE = "DELETE FROM teachers_courses WHERE teacher_id = ? AND course_id = ?";
    private static final String COURSES_TEACHERS= "SELECT t.id, t.name, t.last_name, c.id AS course_id, c.course_name "
            + "FROM teachers AS t "
            + "JOIN teachers_courses AS tc ON tc.teacher_id = t.id "
            + "JOIN courses AS c ON c.id = tc.course_id "
            + "WHERE t.id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TeacherDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Teacher> findAll() {
        return jdbcTemplate.query(ALL, new BeanPropertyRowMapper<>(Teacher.class));
    }

    public Teacher findById(int id) {
        return jdbcTemplate.query(BY_ID, new BeanPropertyRowMapper<>(Teacher.class), id)
                .stream().findAny().orElse(null);
    }

    public void add(Teacher teacher) {
        jdbcTemplate.update(ADDITION, teacher.getName(), teacher.getLastName());
    }

    public void delete(Teacher teacher) {
        jdbcTemplate.update(DELETE, teacher.getId());
    }

    public void addFromCourse(int idTeacher, int idCourse) {
        jdbcTemplate.update(ADD_COURSE, idTeacher, idCourse);
    }

    public void deleteFromCourse(int idTeacher, int idCourse) {
        jdbcTemplate.update(DELETE_COURSE, idTeacher, idCourse);
    }

    public Teacher findCoursesTeacher(Teacher teacher) {
        return jdbcTemplate.query(COURSES_TEACHERS, new TeacherResultExtractor(),teacher.getId());
    }

}
