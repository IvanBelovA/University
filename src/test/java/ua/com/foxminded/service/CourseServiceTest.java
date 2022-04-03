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

import ua.com.foxminded.university.dao.CourseDao;
import ua.com.foxminded.university.model.Course;
import ua.com.foxminded.university.service.impl.CourseServiceImpl;

@ExtendWith(SpringExtension.class)
class CourseServiceTest {

    @Mock
    private CourseDao courseDao;

    @InjectMocks
    private CourseServiceImpl courseService;

    @Test
    void givenReturnListWithAllCoursesWhenCallHim() {
        //given
        List<Course> expected = Arrays.asList(new Course(1, "math"),
                new Course(2, "biology"));
        //when
        when(courseDao.findAll()).thenReturn(expected);
        List<Course> actual = courseService.findAll();

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenReturnCourseWhenDeliverId() {
        //given
        int id = 1;
        Course expected = new Course(id, "math");

        //when
        when(courseDao.findById(id)).thenReturn(expected);
        Course actual = courseService.findById(id);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenCallAddMthodOneTimesWhenDeliverCourse() {
        //given
        CourseServiceImpl courseService = Mockito.mock(CourseServiceImpl.class); 
        Course course = new Course(1, "math");

        //when
        courseService.add(course);

        //then
        verify(courseService, times(1)).add(course);
    }

    @Test
    void givenCallDeleteMthodOneTimesWhenDeliverCourse() {
        //given
        CourseServiceImpl courseService = Mockito.mock(CourseServiceImpl.class); 
        Course course = new Course(1, "math");

        //when
        courseService.delete(course);

        //then
        verify(courseService, times(1)).delete(course);
    }

    @Test
    void givenReturnCourseWhenDeliverCourse() {
        //given
        Course expected = new Course(1, "math");

        //when
        when(courseDao.findCourseStudents(expected)).thenReturn(expected);
        Course actual = courseDao.findCourseStudents(expected);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenReturnCourseWithTeacherWhenDeliverCourse() {
        //given
        Course expected = new Course(1, "math");

        //when
        when(courseDao.findCourseTeachers(expected)).thenReturn(expected);
        Course actual = courseDao.findCourseTeachers(expected);

        //then
        assertEquals(expected, actual);
    }

}
