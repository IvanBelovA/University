package ua.com.foxminded.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ua.com.foxminded.university.dao.CourseDao;
import ua.com.foxminded.university.model.Course;

@Slf4j
@Service
public class CourseServiceImpl {

    private CourseDao courseDao;

    @Autowired
    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public List<Course> findAll() {
        log.debug("Find all course");
        return courseDao.findAll();
    }

    public Course findById(int id) {
        log.debug("Find course with id = {}", id);
        return courseDao.findById(id);
    }

    public void add(Course course) {
        log.debug("Add course with course name - {}", course.getCourseName());
        courseDao.add(course);
    }

    public void delete(Course course) {
        log.debug("Delete course with id = {}", course.getId());
        courseDao.delete(course);
    }

    public Course findCourseStudents(Course course) {
        log.debug("Find all students related course");
        return courseDao.findCourseStudents(course);
    }

    public Course findCourseTeachers(Course course) {
        log.debug("Find all teacher related course");
        return courseDao.findCourseTeachers(course);
    }

}
