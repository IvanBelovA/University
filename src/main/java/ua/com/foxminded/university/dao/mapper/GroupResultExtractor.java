package ua.com.foxminded.university.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import ua.com.foxminded.university.model.Group;
import ua.com.foxminded.university.model.Student;

public class GroupResultExtractor implements ResultSetExtractor<Group> {

    @Override
    public Group extractData(ResultSet rs) throws SQLException, DataAccessException {
        Group group = new Group();
        List<Student> students = new ArrayList<>();

        int row = 0;
        while (rs.next()) {
            if (row == 0) {
                group.setId(rs.getInt("group_id"));
                group.setGroupName(rs.getString("group_name"));
                row++;
            } 
            Student student = new Student(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"));
            students.add(student);
        }

        group.setStudents(students);
        return group;
    }

}
