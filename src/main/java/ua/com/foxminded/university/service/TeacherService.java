package ua.com.foxminded.university.service;

import java.util.List;

import ua.com.foxminded.university.model.Teacher;

public interface TeacherService {

    List<Teacher> findAll();
    Teacher findById(int id);
    void add(Teacher teacher);
    void delete(Teacher teacher);
    void addFromCourse(int idTeacher, int idCourse);
    void deleteFromCourse(int idTeacher, int idCourse);
}
