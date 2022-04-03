package ua.com.foxminded.university.service;

import java.util.List;

import ua.com.foxminded.university.model.Course;

public interface CourseService {

    List<Course> findAll();
    Course findById(int id);
    void add(Course course);
    void delete(Course course);
    Course findCourseStudents(Course course);
    Course findCourseTeachers(Course course);
}
