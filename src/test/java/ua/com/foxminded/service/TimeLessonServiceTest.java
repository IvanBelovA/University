package ua.com.foxminded.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ua.com.foxminded.university.dao.TimeLessonDao;
import ua.com.foxminded.university.model.TimeLesson;
import ua.com.foxminded.university.service.impl.TimeLessonServiceImpl;

@ExtendWith(SpringExtension.class)
class TimeLessonServiceTest {

    @Mock
    private TimeLessonDao timeLessonDao;

    @InjectMocks
    private TimeLessonServiceImpl timeLessonService;

    @Test
    void givenReturnListWithAllTimeLessonWhenCallHim() {
        //given
        LocalDateTime time = LocalDateTime.of(2020, 12, 12, 10, 00, 00);
        TimeLesson timeLesson = new TimeLesson(1, time);
        List<TimeLesson> expected = Arrays.asList(timeLesson);

        //when
        when(timeLessonDao.findAll()).thenReturn(expected);
        List<TimeLesson> actual = timeLessonService.findAll();

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenReturnTimeLessWhenDeliverId() {
        //given
        int id = 1;
        LocalDateTime time = LocalDateTime.of(2020, 12, 12, 10, 00, 00);
        TimeLesson expected = new TimeLesson(id, time);

        //when
        when(timeLessonDao.findById(id)).thenReturn(expected);
        TimeLesson actual = timeLessonService.findById(id);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenCallAddMthodOneTimesWhenDeliverTimeLesson() {
        //given
        TimeLessonServiceImpl timeLessonService = Mockito.mock(TimeLessonServiceImpl.class); 
        LocalDateTime time = LocalDateTime.of(2020, 12, 12, 10, 00, 00);
        TimeLesson timeLesson = new TimeLesson(1, time);

        //when
        timeLessonService.add(timeLesson);

        //then
        verify(timeLessonService, times(1)).add(timeLesson);
    }

    @Test
    void givenCallDeleteMthodOneTimesWhenDeliverTimeLesson() {
        //given
        TimeLessonServiceImpl timeLessonService = Mockito.mock(TimeLessonServiceImpl.class); 
        LocalDateTime time = LocalDateTime.of(2020, 12, 12, 10, 00, 00);
        TimeLesson timeLesson = new TimeLesson(1, time);

        //when
        timeLessonService.delete(timeLesson);

        //then
        verify(timeLessonService, times(1)).delete(timeLesson);
    }

}
