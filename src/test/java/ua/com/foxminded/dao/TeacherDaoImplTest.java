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
import ua.com.foxminded.university.dao.impl.TeacherDaoImpl;
import ua.com.foxminded.university.model.Course;
import ua.com.foxminded.university.model.Teacher;

@SpringBootTest(classes = TestConfiguration.class)
class TeacherDaoImplTest {

    private static final Integer ROWS_AFTER_ADD = 3;
    private static final Integer ROWS_AFTER_DELETE = 1;
    private static final Integer ROWS_AFTER_ADD_COURSE = 4;
    private static final Integer ROWS_AFTER_DELETE_COURSE = 2;

    private RowCounter rowCounter;
    private TestConfiguration configuration;
    private AnnotationConfigApplicationContext context;
    private TeacherDaoImpl teacherDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        rowCounter = new RowCounter();
        configuration = new TestConfiguration();
        context = configuration.getContext();
        teacherDao = new TeacherDaoImpl(jdbcTemplate);
    }

    @AfterEach
    void closeUp() {
        context.close();
    }

    @Test
    void givenReturnListWithAllTeachersWhenCallHim() {
        //given
        List<Teacher> expected = Arrays.asList(new Teacher(1, "Ivan", "Petrov"), new Teacher(2, "Petr", "Ivanov"));

        //when
        List<Teacher> actual = teacherDao.findAll();

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenReturnTeacherByIdWhenDeliverTeacher() {
        //given
        int id = 1;
        Teacher expected = new Teacher(id, "Ivan", "Petrov");

        //when
        Teacher actual = teacherDao.findById(id);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenAddTeacherInDatabseWhenDeliverTeacher() {
        //given
        Integer expected = ROWS_AFTER_ADD;
        Teacher teacher = new Teacher(1, "Ivan", "Petrov");

        //when
        teacherDao.add(teacher);
        Integer actual = rowCounter.getRowsTeacher(jdbcTemplate);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenDeleteTeacherWhenDeliverTeacher() {
        //given
        Integer expected = ROWS_AFTER_DELETE;
        Teacher teacher = new Teacher(1, "Ivan", "Petrov");

        //when
        teacherDao.delete(teacher);
        Integer actual = rowCounter.getRowsTeacher(jdbcTemplate);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenAddSTeacherFromCourseWhenDeliverTeacherAndCourse() {
        //given
        int courseId = 2;
        int studentId = 2;
        Integer expected = ROWS_AFTER_ADD_COURSE;

        //when
        teacherDao.addFromCourse(courseId, studentId);
        Integer actual = rowCounter.getRowsTeachersCourses(jdbcTemplate);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenDeleteTeacherWithCourseWhenDeliverTeacherAndCourse() {
        //given
        int courseId = 1;
        int studentId = 1;
        Integer expected = ROWS_AFTER_DELETE_COURSE;

        //when
        teacherDao.deleteFromCourse(courseId, studentId);
        Integer actual = rowCounter.getRowsTeachersCourses(jdbcTemplate);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenReturnTeacherWithRelatedCoursesWhenDeliverTeacher() {
        //given
        Teacher expected = new Teacher(1, "Ivan", "Petrov");
        List<Course> courses = Arrays.asList(new Course(1, "math"), new Course(2, "biology"));
        expected.setCourses(courses);

        //when
        Teacher actual = teacherDao.findCoursesTeacher(expected);

        //then
        assertEquals(expected, actual);
    }

}
