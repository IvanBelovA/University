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

import ua.com.foxminded.university.dao.LessonDao;
import ua.com.foxminded.university.model.Lesson;
import ua.com.foxminded.university.service.impl.LessonServiceImpl;

@ExtendWith(SpringExtension.class)
class LessonServiceTest {

    @Mock
    private LessonDao lessonDao;

    @InjectMocks
    private LessonServiceImpl lessonService;

    @Test
    void givenReturnListWithLessonsWhenCallHim() {
        //given
        List<Lesson> expected = Arrays.asList(new Lesson(1, 1), new Lesson(2, 2));
        //when
        when(lessonDao.findAll()).thenReturn(expected);
        List<Lesson> actual = lessonService.findAll();

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenReturnLessonWhenDeliverId() {
        //given
        int id = 1;
        Lesson expected = new Lesson(id, 1);

        //when
        when(lessonDao.findById(id)).thenReturn(expected);
        Lesson actual = lessonService.findById(id);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenCallAddMthodOneTimesWhenDeliverLesson() {
        //given
        LessonServiceImpl lessonService = Mockito.mock(LessonServiceImpl.class); 
        Lesson lesson = new Lesson(1, 1);

        //when
        lessonService.add(lesson);

        //then
        verify(lessonService, times(1)).add(lesson);
    }

    @Test
    void givenCallDeleteMthodOneTimesWhenDeliverLesson() {
        //given
        LessonServiceImpl lessonService = Mockito.mock(LessonServiceImpl.class); 
        Lesson lesson = new Lesson(1, 1);

        //when
        lessonService.delete(lesson);

        //then
        verify(lessonService, times(1)).delete(lesson);
    }

}
