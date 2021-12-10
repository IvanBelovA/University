package ua.com.foxminded.university.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.com.foxminded.university.dao.SheduleDao;
import ua.com.foxminded.university.dto.SheduleDto;

@Repository
public class SheduleDaoImpl implements SheduleDao{

    private static final String ALL = "SELECT * FROM shedule";
    private static final String BY_ID = "SELECT * FROM shedule WHERE id = ?";
    private static final String ADDITION = "INSERT INTO shedule"
            + "(lesson_id, group_id, course_id, teacher_id, classroom_id ) "
            + "VALUES(?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM shedule WHERE id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public SheduleDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<SheduleDto> findAll() {
        return jdbcTemplate.query(ALL, new BeanPropertyRowMapper<>(SheduleDto.class));
    }

    public SheduleDto findById(int id) {
        return jdbcTemplate.query(BY_ID, new BeanPropertyRowMapper<>(SheduleDto.class), id)
                .stream().findAny().orElse(null);
    }

    public void add(SheduleDto sheduleDto) {
        jdbcTemplate.update(ADDITION,
                sheduleDto.getLessonId(), sheduleDto.getGroupId(), sheduleDto.getCourseId(),
                sheduleDto.getTeacherId(), sheduleDto.getClassroomId());
    }

    public void delete(SheduleDto sheduleDto) {
        jdbcTemplate.update(DELETE, sheduleDto.getId());
    }

}
