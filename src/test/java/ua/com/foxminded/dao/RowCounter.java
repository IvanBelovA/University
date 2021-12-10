package ua.com.foxminded.dao;

import org.springframework.jdbc.core.JdbcTemplate;

public class RowCounter {

    public Integer getRowsStudents(JdbcTemplate jdbcTemplate) {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM students", Integer.class);
    }

    public Integer getRowsStudentsCourses(JdbcTemplate jdbcTemplate) {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM students_courses", Integer.class);
    }

    public Integer getRowsClassRoom(JdbcTemplate jdbcTemplate) {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM class_room", Integer.class);
    }

    public Integer getRowsCourse(JdbcTemplate jdbcTemplate) {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM courses", Integer.class);
    }

    public Integer getRowsGroup(JdbcTemplate jdbcTemplate) {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM groups", Integer.class);
    }

    public Integer getRowsLesson(JdbcTemplate jdbcTemplate) {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM lessons", Integer.class);
    }

    public Integer getRowsShedule(JdbcTemplate jdbcTemplate) {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM shedule", Integer.class);
    }

    public Integer getRowsTeacher(JdbcTemplate jdbcTemplate) {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM teachers", Integer.class);
    }

    public Integer getRowsTeachersCourses(JdbcTemplate jdbcTemplate) {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM teachers_courses", Integer.class);
    }

    public Integer getRowsTimeLesson(JdbcTemplate jdbcTemplate) {
        return jdbcTemplate.queryForObject("SELECT count(*) FROM time_lesson", Integer.class);
    }

}
