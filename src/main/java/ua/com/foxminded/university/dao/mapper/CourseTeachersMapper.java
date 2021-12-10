package ua.com.foxminded.university.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import ua.com.foxminded.university.model.Course;
import ua.com.foxminded.university.model.Teacher;

public class CourseTeachersMapper implements RowMapper<Course> {

    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        Course course = new Course();
        List<Teacher> teachers = new ArrayList<>();
        while (rs.next()) {
            Teacher teacher = (new BeanPropertyRowMapper<>(Teacher.class)).mapRow(rs, rowNum);
            teachers.add(teacher);
            rowNum++;
        }
        course.setTeachers(teachers);
        return course;
    }

}
