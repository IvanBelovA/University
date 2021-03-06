package ua.com.foxminded.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ua.com.foxminded.university.dao.TeacherDao;
import ua.com.foxminded.university.model.Teacher;
import ua.com.foxminded.university.service.impl.TeacherServiceImpl;

@ExtendWith(SpringExtension.class)
class TeacherServiceTest {

    @Mock
    private TeacherDao teachertDao;

    @InjectMocks
    private TeacherServiceImpl teacherService;

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
        Teacher actual = teacherService.findById(id);

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
