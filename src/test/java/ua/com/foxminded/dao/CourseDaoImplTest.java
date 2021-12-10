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
import ua.com.foxminded.university.dao.impl.CourseDaoImpl;
import ua.com.foxminded.university.model.Course;
import ua.com.foxminded.university.model.Student;

@SpringBootTest(classes = TestConfiguration.class)
class CourseDaoImplTest {

    private static final Integer ROWS_AFTER_ADD = 3;
    private static final Integer ROWS_AFTER_DELETE = 1;

    private RowCounter rowCounter;
    private TestConfiguration configuration;
    private AnnotationConfigApplicationContext context;
    private CourseDaoImpl courseDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        rowCounter = new RowCounter();
        configuration = new TestConfiguration();
        context = configuration.getContext();
        courseDao = new CourseDaoImpl(jdbcTemplate);
    }

    @AfterEach
    void closeUp() {
        context.close();
    }

    @Test
    void givenReturnListWithAllCoursesWhenCallHim() {
        //given
        List<Course> expected = Arrays.asList(new Course(1, "math"),
                new Course(2, "biology"));

        //when
        List<Course> actual = courseDao.findAll();

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenReturnCourseByIdWhenDeliverCourse() {
        //given
        int id = 1;
        Course expected = new Course(id, "math");

        //when
        Course actual = courseDao.findById(id);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenAddCourseInDatabseWhenDeliverCourse() {
        //given
        Integer expected = ROWS_AFTER_ADD;
        Course course = new Course("history");

        //when
        courseDao.add(course);
        Integer actual = rowCounter.getRowsCourse(jdbcTemplate);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenCourseWhenDeliverCourse() {
        //given
        Course course = new Course(1, "math");
        Integer expected = ROWS_AFTER_DELETE;

        //when
        courseDao.delete(course);
        Integer actual = rowCounter.getRowsCourse(jdbcTemplate);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenReturnCourseWithAllRelatedStudentsWhenDeliverCourse() {
        //given
        Course expectedq = new Course(1, "math");
        Course expected = new Course(1, "math");
        List<Student> students = Arrays.asList(new Student(1, "Ivan", "Petrov"), new Student(2, "Petr", "Ivanov"));
        expected.setStudents(students);

        //when
        Course actual = courseDao.findCourseStudents(expectedq);

        //
        assertEquals(expected, actual);
    }

}
