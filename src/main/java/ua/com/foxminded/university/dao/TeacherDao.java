package ua.com.foxminded.university.dao;

import ua.com.foxminded.university.model.Teacher;

public interface TeacherDao extends GenericDao<Teacher> {

    public void addFromCourse(int idTeacher, int idCourse);

    public void deleteFromCourse(int idTeacher, int idCourse);

    Teacher findCoursesTeacher(Teacher teacher);
}
