package ua.com.foxminded.university.dao;

import ua.com.foxminded.university.model.Student;

public interface StudentDao extends GenericDao<Student>{

    void assignFromCourse(int studentId, int courseId);

    void addFromGroup(int studentId, int groupId);

    void deleteFromGroup(int studentId);

    void deleteFromCourse(int studentId, int courseId);

    Student findCoursesStudent(Student student);
}
