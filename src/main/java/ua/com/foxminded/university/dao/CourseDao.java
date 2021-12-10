package ua.com.foxminded.university.dao;

import ua.com.foxminded.university.model.Course;

public interface CourseDao extends GenericDao<Course> {

    Course findCourseStudents(Course course);

    Course findCourseTeachers(Course course);
}
