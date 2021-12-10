package ua.com.foxminded.university.model;

import java.util.List;

import lombok.Data;

@Data
public class Student {

    private int id;
    private String firstName;
    private String lastName;
    private Group group;
    private List<Course> courses;

    public Student(String name, String lastName) {
        this.firstName = name;
        this.lastName = lastName;
    }

    
    public Student() {}

    public Student(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
