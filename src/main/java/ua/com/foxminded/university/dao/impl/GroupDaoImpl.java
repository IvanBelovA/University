package ua.com.foxminded.university.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.university.dao.GroupDao;
import ua.com.foxminded.university.dao.mapper.GroupResultExtractor;
import ua.com.foxminded.university.model.Group;

@Repository
public class GroupDaoImpl implements GroupDao {

    private static final String ALL = "SELECT * FROM groups";
    private static final String BY_ID = "SELECT * FROM groups WHERE id = ?";
    private static final String ADDITION = "INSERT INTO groups(group_name) VALUES(?)";
    private static final String DELETE = "DELETE FROM groups WHERE id = ?";
    private static final String GROUP_STUDENTS = "SELECT g.id AS group_id, g.group_name, "
            + "s.id, s.first_name, s.last_name "
            + "FROM groups AS g "
            + "JOIN students AS s ON s.group_id = g.id "
            + "WHERE g.id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public GroupDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Group> findAll() {
        return jdbcTemplate.query(ALL, new BeanPropertyRowMapper<>(Group.class));
    }

    public Group findById(int id) {
        return jdbcTemplate.query(BY_ID, new BeanPropertyRowMapper<>(Group.class), id)
                .stream().findAny().orElse(null);
    }

    public void add(Group group) {
        jdbcTemplate.update(ADDITION, group.getGroupName());
    }

    public void delete(Group group) {
        jdbcTemplate.update(DELETE, group.getId());
    }

    public Group findGroupStudents(Group group) {
        return jdbcTemplate.query(GROUP_STUDENTS, new GroupResultExtractor(), group.getId());
    }

}
