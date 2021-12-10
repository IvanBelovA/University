package ua.com.foxminded.university.dao.mapper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import ua.com.foxminded.university.model.Course;
import ua.com.foxminded.university.model.Student;

public class StudentsResultExtractor implements ResultSetExtractor<Student> {

    @Override
    public Student extractData(java.sql.ResultSet rs) throws SQLException, DataAccessException {
        Student student = new Student();
        List<Course> courses = new ArrayList<>();

        int row = 0;
        while (rs.next()) {
            if (row == 0) {
                student.setId(rs.getInt("id"));
                student.setFirstName(rs.getString("first_name"));
                student.setLastName(rs.getString("last_name"));
                row++;
            } 
            Course course = new Course(rs.getInt("course_id"), rs.getString("course_name"));
            courses.add(course);
        }

        student.setCourses(courses);
        return student;
    }

}
