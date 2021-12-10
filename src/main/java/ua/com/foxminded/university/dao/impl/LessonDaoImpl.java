package ua.com.foxminded.university.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.university.dao.LessonDao;
import ua.com.foxminded.university.model.Lesson;

@Repository
public class LessonDaoImpl implements LessonDao{

    private static final String ALL = "SELECT * FROM lessons";
    private static final String BY_ID = "SELECT * FROM lessons WHERE id = ?";
    private static final String ADDITION = "INSERT INTO lessons(number, time_id) VALUES(?, ?)";
    private static final String DELETE = "DELETE FROM lessons WHERE id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public LessonDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Lesson> findAll() {
        return jdbcTemplate.query(ALL, new BeanPropertyRowMapper<>(Lesson.class));
    }

    public Lesson findById(int id) {
        return jdbcTemplate.query(BY_ID, new BeanPropertyRowMapper<>(Lesson.class), id)
                .stream().findAny().orElse(null);
    }

    public void add(Lesson lesson) {
        jdbcTemplate.update(ADDITION, lesson.getNumber(), lesson.getTimeLesson());
    }

    public void delete(Lesson lesson) {
        jdbcTemplate.update(DELETE, lesson.getId());
    }

}
