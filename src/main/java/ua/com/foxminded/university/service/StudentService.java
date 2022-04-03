package ua.com.foxminded.university.service;

import java.util.List;

import ua.com.foxminded.university.model.Student;

public interface StudentService {

    List<Student> findAll();
    Student findById(int id);
    void add(Student student);
    void delete(Student student);
    void assignFromCourse(int studentId, int courseId);
    void deleteFromCourse(int studentId, int courseId);
    void addFromGroup(int studentId, int groupId);
    void deleteFromGroup(int studentId);
    Student findCoursesStudent(Student student);
}
