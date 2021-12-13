package ua.com.foxminded.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.dao.TeacherDao;
import ua.com.foxminded.university.model.Teacher;

@Service
public class TeacherServiceImpl {

    private TeacherDao teacherDao;

    @Autowired
    public TeacherServiceImpl(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    public List<Teacher> findAll() {
        return teacherDao.findAll();
    }

    public Teacher findById(int id) {
        return teacherDao.findById(id);
    }

    public void add(Teacher teacher) {
        teacherDao.add(teacher);
    }

    public void delete(Teacher teacher) {
        teacherDao.delete(teacher);
    }

    public void addFromCourse(int idTeacher, int idCourse) {
        teacherDao.addFromCourse(idTeacher, idCourse);
    }

    public void deleteFromCourse(int idTeacher, int idCourse) {
        teacherDao.deleteFromCourse(idTeacher, idCourse);
    }

}
