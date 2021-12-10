package ua.com.foxminded.university.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import ua.com.foxminded.university.model.Course;
import ua.com.foxminded.university.model.Teacher;

public class TeacherResultExtractor implements ResultSetExtractor<Teacher>{

    @Override
    public Teacher extractData(ResultSet rs) throws SQLException, DataAccessException {
        Teacher teacher = new Teacher();
        List<Course> courses = new ArrayList<>();

        int row = 0;
        while (rs.next()) {
            if (row == 0) {
                teacher.setId(rs.getInt("id"));
                teacher.setName(rs.getString("name"));
                teacher.setLastName(rs.getString("last_name"));
                row++;
            } 
            Course course = new Course(rs.getInt("course_id"), rs.getString("course_name"));
            courses.add(course);
        }

        teacher.setCourses(courses);
        return teacher;
    }

}
