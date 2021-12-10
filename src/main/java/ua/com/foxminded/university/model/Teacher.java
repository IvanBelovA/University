package ua.com.foxminded.university.model;

import java.util.List;

import lombok.Data;

@Data
public class Teacher {

    private int id;
    private String name;
    private String lastName;
    private List<Course> courses;

    public Teacher(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public Teacher() {
    }

    public Teacher(int id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
    }

}
