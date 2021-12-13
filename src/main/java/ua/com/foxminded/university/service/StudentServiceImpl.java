package ua.com.foxminded.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.dao.StudentDao;
import ua.com.foxminded.university.model.Student;

@Service
public class StudentServiceImpl {

    private StudentDao studentDao;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public List<Student> findAll() {
        return studentDao.findAll();
    }

    public Student findById(int id) {
        return studentDao.findById(id);
    }

    public void add(Student student) {
        studentDao.add(student);
    }

    public void delete(Student student) {
        studentDao.delete(student);
    }

    public void assignFromCourse(int studentId, int courseId) {
        studentDao.assignFromCourse(studentId, courseId);
    }

    public void deleteFromCourse(int studentId, int courseId) {
        studentDao.deleteFromCourse(studentId, courseId);
    }

    public void addFromGroup(int studentId, int groupId) {
        studentDao.addFromGroup(studentId, groupId);
    }

    public void deleteFromGroup(int studentId) {
        studentDao.deleteFromGroup(studentId);
    }

    public Student findCoursesStudent(Student student) {
        return studentDao.findCoursesStudent(student);
    }

}
