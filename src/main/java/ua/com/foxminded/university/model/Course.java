package ua.com.foxminded.university.model;

import java.util.List;

import lombok.Data;

@Data
public class Course {

    private int id;
    private String courseName;
    private List<Student> students;
    private List<Teacher> teachers;

    public Course(String name) {
        this.courseName = name;
    }

    public Course() {}

    public Course(int id, String courseName) {
        this.id = id;
        this.courseName = courseName;
    }
}
