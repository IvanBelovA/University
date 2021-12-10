package ua.com.foxminded.dao;

import static org.junit.jupiter.api.Assertions.*;

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
import ua.com.foxminded.university.dao.impl.LessonDaoImpl;
import ua.com.foxminded.university.model.Lesson;

@SpringBootTest(classes = TestConfiguration.class)
class LessonDaoImplTest {

    private static final Integer ROWS_AFTER_ADD = 3;
    private static final Integer ROWS_AFTER_DELETE = 1;

    private RowCounter rowCounter;
    private TestConfiguration configuration;
    private AnnotationConfigApplicationContext context;
    private LessonDaoImpl lessonDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        rowCounter = new RowCounter();
        configuration = new TestConfiguration();
        context = configuration.getContext();
        lessonDao = new LessonDaoImpl(jdbcTemplate);
    }

    @AfterEach
    void closeUp() {
        context.close();
    }

    @Test
    void givenReturnListWithAllLessonWhenCallHim() {
        //given
        List<Lesson> expected = Arrays.asList(new Lesson(1, 1), new Lesson(2, 2));

        //when
        List<Lesson> actual = lessonDao.findAll();

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenReturnLessonByIdWhenDeliverLesson() {
        //given
        int id = 1;
        Lesson expected = new Lesson(id, 1);

        //when
        Lesson actual = lessonDao.findById(id);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenAddLessonInDatabseWhenDeliverLesson() {
        //given
        Integer expected = ROWS_AFTER_ADD;
        Lesson lesson = new Lesson(3, 3);

        //when
        lessonDao.add(lesson);
        Integer actual = rowCounter.getRowsLesson(jdbcTemplate);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenDeleteGroupWhenDeliverGroup() {
        //given
        Lesson lesson = new Lesson(1, 1);
        Integer expected = ROWS_AFTER_DELETE;

        //when
        lessonDao.delete(lesson);
        Integer actual = rowCounter.getRowsLesson(jdbcTemplate);

        //then
        assertEquals(expected, actual);
    }

}
