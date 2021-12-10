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
import ua.com.foxminded.university.dao.impl.GroupDaoImpl;
import ua.com.foxminded.university.model.Group;
import ua.com.foxminded.university.model.Student;

@SpringBootTest(classes = TestConfiguration.class)
class GroupDaoImplTest {

    private static final Integer ROWS_AFTER_ADD = 3;
    private static final Integer ROWS_AFTER_DELETE = 1;

    private RowCounter rowCounter;
    private TestConfiguration configuration;
    private AnnotationConfigApplicationContext context;
    private GroupDaoImpl groupDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        rowCounter = new RowCounter();
        configuration = new TestConfiguration();
        context = configuration.getContext();
        groupDao = new GroupDaoImpl(jdbcTemplate);
    }

    @AfterEach
    void closeUp() {
        context.close();
    }

    @Test
    void givenReturnListWithAllGroupsWhenCallHim() {
        //given
        List<Group> expected = Arrays.asList(new Group(1, "AA-11"), new Group(2, "BB-22"));

        //when
        List<Group> actual = groupDao.findAll();

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenReturnGroupByIdWhenDeliverGroup() {
        //given
        int id = 1;
        Group expected = new Group(id, "AA-11");

        //when
        Group actual = groupDao.findById(id);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenAddGroupInDatabseWhenDeliverGroup() {
        //given
        Integer expected = ROWS_AFTER_ADD;
        Group group = new Group("CC-33");

        //when
        groupDao.add(group);
        Integer actual = rowCounter.getRowsGroup(jdbcTemplate);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenDeleteGroupWhenDeliverGroup() {
        //given
        Group group = new Group(1, "AA-11");
        Integer expected = ROWS_AFTER_DELETE;

        //when
        groupDao.delete(group);
        Integer actual = rowCounter.getRowsGroup(jdbcTemplate);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenReturnGroupWithAllRelatedStudentsWhenDeliverGroup() {
        //given
        Group expected = new Group(1, "AA-11");
        List<Student> students = Arrays.asList(new Student(1, "Ivan", "Petrov"), new Student(2, "Petr", "Ivanov"));
        expected.setStudents(students);

        //when
        Group actual = groupDao.findGroupStudents(expected);

        //then
        assertEquals(expected, actual);
    }

}
