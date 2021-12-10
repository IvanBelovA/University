package ua.com.foxminded.university.model;

import java.util.List;

import lombok.Data;

@Data
public class Group {

    private int id;
    private String groupName;
    private List<Student> students;

    public Group(String name) {
        this.groupName = name;
    }

    public Group() {}

    public Group(int id, String name) {
        this.id = id;
        this.groupName = name;
    }

}
