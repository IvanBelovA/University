package ua.com.foxminded.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ua.com.foxminded.university.dao.TeacherDao;
import ua.com.foxminded.university.exception.DaoException;
import ua.com.foxminded.university.exception.ServiceException;
import ua.com.foxminded.university.model.Teacher;

@Slf4j
@Service
public class TeacherServiceImpl {

    private TeacherDao teacherDao;

    @Autowired
    public TeacherServiceImpl(TeacherDao teacherDao) {
        this.teacherDao = teacherDao;
    }

    public List<Teacher> findAll() {
        log.debug("Find all teachers");
        return teacherDao.findAll();
    }

    public Teacher findById(int id) {
        log.debug("Find teacher with id = {}", id);
        return teacherDao.findById(id);
    }

    public void add(Teacher teacher) {
        log.debug("Add teacher with name - {}, last name - {}",
                teacher.getName(), teacher.getLastName());
        teacherDao.add(teacher);
    }

    public void delete(Teacher teacher) {
        log.debug("Delete tacher with id = {}", teacher.getId());
        teacherDao.delete(teacher);
    }

    public void addFromCourse(int idTeacher, int idCourse) {
        try {
            log.debug("Add teacher from course with tacher id = {}, course_id = {}",
                    idTeacher, idCourse);
            teacherDao.addFromCourse(idTeacher, idCourse);
        } catch (DaoException e) {
            throw new ServiceException("No techer or course exists");
        }
    }

    public void deleteFromCourse(int idTeacher, int idCourse) {
        log.debug("Delete teacher from course with tacher id = {}, course_id = {}",
                idTeacher, idCourse);
        teacherDao.deleteFromCourse(idTeacher, idCourse);
    }

}
