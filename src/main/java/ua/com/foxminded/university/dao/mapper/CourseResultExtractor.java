package ua.com.foxminded.university.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import ua.com.foxminded.university.model.Course;
import ua.com.foxminded.university.model.Student;

public class CourseResultExtractor implements ResultSetExtractor<Course> {

    @Override
    public Course extractData(ResultSet rs) throws SQLException, DataAccessException {
        Course course = new Course();
        List<Student> students = new ArrayList<>();

        int row = 0;
        while (rs.next()) {
            if (row == 0) {
                course.setId(rs.getInt("course_id"));
                course.setCourseName(rs.getString("course_name"));
                row++;
            } 
            Student student = new Student(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"));
            students.add(student);
        }

        course.setStudents(students);
        return course;
    }

}
