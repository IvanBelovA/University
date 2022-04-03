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

import ua.com.foxminded.university.dao.StudentDao;
import ua.com.foxminded.university.model.Student;
import ua.com.foxminded.university.service.impl.StudentServiceImpl;

@ExtendWith(SpringExtension.class)
class StudentServiceTest {

    @Mock
    private StudentDao studentDao;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    void givenReturnListWithStudentsWhenCallHim() {
        //given
        List<Student> expected = Arrays.asList(new Student(1, "Ivan", "Petrov"),
                new Student(2, "Petr", "Ivanov"));

        //when
        when(studentDao.findAll()).thenReturn(expected);
        List<Student> actual = studentService.findAll();

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenReturnStudentWhenDeliverId() {
        //given
        int id = 1;
        Student expected = new Student(id, "Ivan", "Petrov");

        //when
        when(studentDao.findById(id)).thenReturn(expected);
        Student actual = studentService.findById(id);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenCallAddMthodOneTimesWhenDeliverStudent() {
        //given
        StudentServiceImpl studentService = Mockito.mock(StudentServiceImpl.class); 
        Student student = new Student(1, "Ivan", "Petrov");

        //when
        studentService.add(student);

        //then
        verify(studentService, times(1)).add(student);
    }

    @Test
    void givenCallDeleteMthodOneTimesWhenDeliverStudent() {
        //given
        StudentServiceImpl studentService = Mockito.mock(StudentServiceImpl.class); 
        Student student = new Student(1, "Ivan", "Petrov");

        //when
        studentService.delete(student);

        //then
        verify(studentService, times(1)).delete(student);
    }

    @Test
    void givenCallMethodAssignFromCourseOneTimeWhenDeliverId() {
        //given
        int studentId = 1;
        int courseId = 1;
        StudentServiceImpl studentService = Mockito.mock(StudentServiceImpl.class); 

        //when
        studentService.assignFromCourse(studentId, courseId);

        //then
        verify(studentService, times(1)).assignFromCourse(studentId, courseId);
    }

    @Test
    void givenCallMethodDeleteFromCourseOneTimeWhenDeliverId() {
        //given
        int studentId = 1;
        int courseId = 1;
        StudentServiceImpl studentService = Mockito.mock(StudentServiceImpl.class); 

        //when
        studentService.deleteFromCourse(studentId, courseId);

        //then
        verify(studentService, times(1)).deleteFromCourse(studentId, courseId);  
    }

    @Test
    void givenCallMethodAddFromGroupOneTimeWhenDeliverId() {
        //given
        int studentId = 1;
        int groupId = 1;
        StudentServiceImpl studentService = Mockito.mock(StudentServiceImpl.class); 

        //when
        studentService.addFromGroup(studentId, groupId);

        //then
        verify(studentService, times(1)).addFromGroup(studentId, groupId); 
    }

    @Test
    void givenCallMethodDeleteFromGroupOneTimeWhenDeliverId() {
        //given
        int studentId = 1;
        StudentServiceImpl studentService = Mockito.mock(StudentServiceImpl.class); 

        //when
        studentService.deleteFromGroup(studentId);

        //then
        verify(studentService, times(1)).deleteFromGroup(studentId); 
    }

}
