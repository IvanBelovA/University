package ua.com.foxminded.university.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.university.dao.TimeLessonDao;
import ua.com.foxminded.university.model.TimeLesson;

@Repository
public class TimeLessonDaoImpl implements TimeLessonDao{

    private static final String ALL = "SELECT * FROM time_lesson";
    private static final String BY_ID = "SELECT * FROM time_lesson WHERE id = ?";
    private static final String ADDITION = "INSERT INTO time_lesson(times) VALUES(?)";
    private static final String DELETE = "DELETE FROM time_lesson WHERE id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TimeLessonDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<TimeLesson> findAll() {
        return jdbcTemplate.query(ALL, new BeanPropertyRowMapper<>(TimeLesson.class));
    }

    public TimeLesson findById(int id) {
        return jdbcTemplate.query(BY_ID, new BeanPropertyRowMapper<>(TimeLesson.class), id)
                .stream().findAny().orElse(null);
    }

    public void add(TimeLesson timeLesson) {
        jdbcTemplate.update(ADDITION, timeLesson.getTimes());
    }

    public void delete(TimeLesson timeLesson) {
        jdbcTemplate.update(DELETE, timeLesson.getId());
    }

}
