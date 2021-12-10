package ua.com.foxminded.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import ua.com.foxminded.configuration.TestConfiguration;
import ua.com.foxminded.university.dao.impl.TimeLessonDaoImpl;
import ua.com.foxminded.university.model.TimeLesson;

@SpringBootTest(classes = TestConfiguration.class)
public class TimeLessonDaoImplTest {


    private static final Integer ROWS_AFTER_ADD = 2;
    private static final Integer ROWS_AFTER_DELETE = 0;

    private RowCounter rowCounter;
    private TestConfiguration configuration;
    private AnnotationConfigApplicationContext context;
    private TimeLessonDaoImpl timeLessonDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        rowCounter = new RowCounter();
        configuration = new TestConfiguration();
        context = configuration.getContext();
        timeLessonDao = new TimeLessonDaoImpl(jdbcTemplate);
    }

    @AfterEach
    void closeUp() {
        context.close();
    }

    @Test
    void givenReturnListWithAllTimeLessonWhenCallHim() {
        //given
        LocalDateTime time = LocalDateTime.of(2020, 12, 12, 10, 00, 00);
        TimeLesson timeLesson = new TimeLesson(1, time);
        List<TimeLesson> expected = Arrays.asList(timeLesson);

        //when
        List<TimeLesson> actual = timeLessonDao.findAll();

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenReturnTimeLessonWhenDeliverTimeLesson() {
        //given
        int id = 1;
        LocalDateTime time = LocalDateTime.of(2020, 12, 12, 10, 00, 00);
        TimeLesson expected = new TimeLesson(id, time);

        //when
        TimeLesson actual = timeLessonDao.findById(id);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenAddTimeLessonInDatabaseWhenDeliverTimeLesson() {
        //given
        Integer expected = ROWS_AFTER_ADD;
        LocalDateTime time = LocalDateTime.of(2020, 12, 12, 10, 00, 00);
        TimeLesson timeLesson = new TimeLesson(time);;

        //when
        timeLessonDao.add(timeLesson);
        Integer actual = rowCounter.getRowsTimeLesson(jdbcTemplate);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenDeleteTimeLessonInDatabaseWhenDeliverTimeLesson() {
        //given
        Integer expected = ROWS_AFTER_DELETE;
        LocalDateTime time = LocalDateTime.of(2020, 12, 12, 10, 00, 00);
        TimeLesson timeLesson = new TimeLesson(1, time);;

        //when
        timeLessonDao.delete(timeLesson);
        Integer actual = rowCounter.getRowsTimeLesson(jdbcTemplate);

        //then
        assertEquals(expected, actual);
    }

}
