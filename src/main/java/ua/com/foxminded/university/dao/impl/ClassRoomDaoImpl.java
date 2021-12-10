package ua.com.foxminded.university.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.university.dao.ClassRoomDao;
import ua.com.foxminded.university.model.ClassRoom;

@Repository
public class ClassRoomDaoImpl implements ClassRoomDao {

    private static final String ALL = "SELECT * FROM class_room";
    private static final String BY_ID = "SELECT * FROM class_room WHERE id = ?";
    private static final String ADDITION = "INSERT INTO class_room(number, capacity) VALUES(?, ?)";
    private static final String DELETE = "DELETE FROM class_room WHERE id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ClassRoomDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ClassRoom> findAll() {
        return jdbcTemplate.query(ALL, new BeanPropertyRowMapper<>(ClassRoom.class));
    }

    public ClassRoom findById(int id) {
        return jdbcTemplate.query(BY_ID, new BeanPropertyRowMapper<>(ClassRoom.class), id)
                .stream().findAny().orElse(null);
    }

    public void add(ClassRoom classRoom) {
        jdbcTemplate.update(ADDITION, classRoom.getNumber(), classRoom.getCapacity());
    }

    public void delete(ClassRoom classRoom) {
        jdbcTemplate.update(DELETE, classRoom.getId());
    }

}
