package ua.com.foxminded.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ua.com.foxminded.university.dao.StudentDao;
import ua.com.foxminded.university.exception.DaoException;
import ua.com.foxminded.university.exception.ServiceException;
import ua.com.foxminded.university.model.Student;
import ua.com.foxminded.university.service.StudentService;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService{

    private StudentDao studentDao;

    @Autowired
    public StudentServiceImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public List<Student> findAll() {
        log.debug("Find all students");
        return studentDao.findAll();
    }

    public Student findById(int id) {
        log.debug("Find student with id = {}", id);
        return studentDao.findById(id);
    }

    public void add(Student student) {
        log.debug("Add student witth name - {}, last name - {}",
                student.getFirstName(), student.getLastName());
        studentDao.add(student);
    }

    public void delete(Student student) {
        log.debug("Delete student with id = {}", student.getId());
        studentDao.delete(student);
    }

    public void assignFromCourse(int studentId, int courseId) {
        try {
            log.debug("Assign student from course with student_id = {}, course_id = {}",
                    studentId, courseId);
            studentDao.assignFromCourse(studentId, courseId);
        } catch (DaoException e) {
            throw new ServiceException("No student or course exists");
        }
    }

    public void deleteFromCourse(int studentId, int courseId) {
        log.debug("Delete student from course with student_id = {}, course_id = {}",
                studentId, courseId);
        studentDao.deleteFromCourse(studentId, courseId);
    }

    public void addFromGroup(int studentId, int groupId) {
        try {
            log.debug("Add student from group with student_id = {}, group_id = {}",
                    studentId, groupId);
            studentDao.addFromGroup(studentId, groupId);
        } catch (DaoException e) {
            throw new ServiceException("No group exists");
        }
    }

    public void deleteFromGroup(int studentId) {
        log.debug("Delete student from group with student_id = {}",
                studentId);
        studentDao.deleteFromGroup(studentId);
    }

    public Student findCoursesStudent(Student student) {
        log.debug("Find courses related student with id = {}", student.getId());
        return studentDao.findCoursesStudent(student);
    }

}
