package ua.com.foxminded.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ua.com.foxminded.university.dao.TeacherDao;
import ua.com.foxminded.university.model.Teacher;
import ua.com.foxminded.university.service.TeacherServiceImpl;

class TeacherServiceTest {

    private TeacherServiceImpl teacherService;
    private TeacherDao teachertDao;

    @BeforeEach
    void setUp() {
        teachertDao = Mockito.mock(TeacherDao.class);
        teacherService = new TeacherServiceImpl(teachertDao);
    }

    @Test
    void givenReturnListWithAllTeacherWhenCallHim() {
        //given
        List<Teacher> expected = Arrays.asList(new Teacher(1, "Ivan", "Petrov"),
                new Teacher(2, "Petr", "Ivanov"));

        //when
        when(teachertDao.findAll()).thenReturn(expected);
        List<Teacher> actual = teacherService.findAll();

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenReturnTeacherWhenDeliverId() {
        //given
        int id = 1;
        Teacher expected = new Teacher(id, "Ivan", "Petrov");

        //when
        when(teachertDao.findById(id)).thenReturn(expected);
        Teacher actual = teachertDao.findById(id);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenCallAddMthodOneTimesWhenDeliverTeacher() {
        //given
        TeacherServiceImpl teacherService = Mockito.mock(TeacherServiceImpl.class); 
        Teacher teacher = new Teacher(1, "Ivan", "Petrov");

        //when
        teacherService.add(teacher);

        //then
        verify(teacherService, times(1)).add(teacher);
    }

    @Test
    void givenCallDeleteMthodOneTimesWhenDeliverTeacher() {
        //given
        TeacherServiceImpl teacherService = Mockito.mock(TeacherServiceImpl.class); 
        Teacher teacher = new Teacher(1, "Ivan", "Petrov");

        //when
        teacherService.delete(teacher);

        //then
        verify(teacherService, times(1)).delete(teacher);
    }

    @Test
    void givenCallMethodAddFromCourseOneTimeWhenDeliverId() {
        //given
        int teachertId = 1;
        int courseId = 1;
        TeacherServiceImpl teacherService = Mockito.mock(TeacherServiceImpl.class); 

        //when
        teacherService.addFromCourse(teachertId, courseId);

        //then
        verify(teacherService, times(1)).addFromCourse(teachertId, courseId);
    }

    @Test
    void givenCallMethodDeleteFromCourseOneTimeWhenDeliverId() {
        //given
        int teachertId = 1;
        int courseId = 1;
        TeacherServiceImpl teacherService = Mockito.mock(TeacherServiceImpl.class); 

        //when
        teacherService.deleteFromCourse(teachertId, courseId);

        //then
        verify(teacherService, times(1)).deleteFromCourse(teachertId, courseId);
    }

}
