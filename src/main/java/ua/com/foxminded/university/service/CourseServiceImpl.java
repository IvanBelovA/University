package ua.com.foxminded.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.dao.CourseDao;
import ua.com.foxminded.university.model.Course;

@Service
public class CourseServiceImpl {

    private CourseDao courseDao;

    @Autowired
    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    public List<Course> findAll() {
        return courseDao.findAll();
    }

    public Course findById(int id) {
        return courseDao.findById(id);
    }

    public void add(Course course) {
        courseDao.add(course);
    }

    public void delete(Course course) {
        courseDao.delete(course);
    }

    public Course findCourseStudents(Course course) {
        return courseDao.findCourseStudents(course);
    }

    public Course findCourseTeachers(Course course) {
        return courseDao.findCourseTeachers(course);
    }

}
