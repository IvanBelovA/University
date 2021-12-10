package ua.com.foxminded.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import ua.com.foxminded.configuration.TestConfiguration;
import ua.com.foxminded.university.dao.impl.StudentDaoImpl;
import ua.com.foxminded.university.model.Course;
import ua.com.foxminded.university.model.Student;

@SpringBootTest(classes = TestConfiguration.class)
class StudentDaoImplTest {

    private static final Integer ROWS_AFTER_ADD_STUDENT = 3;
    private static final Integer ROWS_AFTER_DELETE_STUDENT = 1;
    private static final Integer ROWS_AFTER_ADD_COURSE = 4;
    private static final Integer ROWS_AFTER_DELETE_COURSE = 2;

    private RowCounter rowCounter;
    private TestConfiguration configuration;
    private AnnotationConfigApplicationContext context;
    private StudentDaoImpl studentDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        rowCounter = new RowCounter();
        configuration = new TestConfiguration();
        context = configuration.getContext();
        studentDao = new StudentDaoImpl(jdbcTemplate);
    }
    @AfterEach
    void closeUp() {
        context.close();
    }

    @Test
    void givenReturnListWithAllStudentsWhenCallHim() {
        //given
        List<Student> expected = Arrays.asList(new Student(1, "Ivan", "Petrov"),
                new Student(2, "Petr", "Ivanov"));

        //when
        List<Student> actual = studentDao.findAll();

        //then
        assertEquals(actual, expected);
    }

    @Test
    void givenReturnStudentByIdWhenDeliverStudent() {
        //given
        int id = 1;
        Student expected = new Student(id, "Ivan", "Petrov");

        //when
        Student actual = studentDao.findById(id);

        //then
        assertEquals(actual, expected);
    }

    @Test
    void givenAddStudentInDatabseWhenDeliverStudent() {
        //given
        Integer expected = ROWS_AFTER_ADD_STUDENT;
        Student student = new Student("Petr", "Ivanov");

        //when
        studentDao.add(student);
        Integer actual = rowCounter.getRowsStudents(jdbcTemplate);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenDeleteStudentWhenDeliverStudent() {
        //given
        Student student = new Student(1, "Ivanov", "Petr");
        Integer expected = ROWS_AFTER_DELETE_STUDENT;

        //when
        studentDao.delete(student);
        Integer actual = rowCounter.getRowsStudents(jdbcTemplate);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenAddStudentFromCourseWhenDeliverStudentAndCourse() {
        //given
        int courseId = 2;
        int studentId = 2;
        Integer expected = ROWS_AFTER_ADD_COURSE;

        //when
        studentDao.assignFromCourse(courseId, studentId);
        Integer actual = rowCounter.getRowsStudentsCourses(jdbcTemplate);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenDeleteStudentFromCourseWhenDeliverStudentAndCourse() {
        //given
        int courseId = 1;
        int studentId = 1;
        Integer expected = ROWS_AFTER_DELETE_COURSE;

        //when
        studentDao.deleteFromCourse(courseId, studentId);
        Integer actual = rowCounter.getRowsStudentsCourses(jdbcTemplate);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenDeleteStudentFromGroupWhenDeliverStudent() {
        //given
        int studentId = 2;
        StudentDaoImpl studentDaoImpl = Mockito.mock(StudentDaoImpl.class);

        //when
        studentDaoImpl.deleteFromGroup(studentId);

        //then
        verify(studentDaoImpl, times(1)).deleteFromGroup(studentId);
    }

    @Test
    void givenAddStudentFromGroupWhenDeliverStudent() {
        //given
        int studentId = 2;
        int groupId = 2;
        StudentDaoImpl studentDaoImpl = Mockito.mock(StudentDaoImpl.class);

        //when
        studentDaoImpl.addFromGroup(studentId, groupId);

        //then
        verify(studentDaoImpl, times(1)).addFromGroup(studentId, groupId);
    }

    @Test
    void givenReturnStudentWithRelatedCoursesWhenDeliverStudent() {
        //given
        Student expected = new Student(1, "Ivan", "Petrov");
        List<Course> courses = Arrays.asList(new Course(1, "math"), new Course(2, "biology"));
        expected.setCourses(courses);

        //when
        Student actual = studentDao.findCoursesStudent(expected);

        //then
        assertEquals(expected, actual);
    }

}
