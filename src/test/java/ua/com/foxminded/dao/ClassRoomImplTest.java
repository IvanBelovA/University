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
import ua.com.foxminded.university.dao.impl.ClassRoomDaoImpl;
import ua.com.foxminded.university.model.ClassRoom;

@SpringBootTest(classes = TestConfiguration.class)
class ClassRoomImplTest {

    private static final Integer ROWS_AFTER_ADD = 3;
    private static final Integer ROWS_AFTER_DELETE = 1;

    private RowCounter rowCounter;
    private TestConfiguration configuration;
    private AnnotationConfigApplicationContext context;
    private ClassRoomDaoImpl classRoomDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        rowCounter = new RowCounter();
        configuration = new TestConfiguration();
        context = configuration.getContext();
        classRoomDao = new ClassRoomDaoImpl(jdbcTemplate);
    }

    @AfterEach
    void closeUp() {
        context.close();
    }

    @Test
    void givenReturnListWithAllClassRoomWhenCallHim() {
        //given
        List<ClassRoom> expected = Arrays.asList(new ClassRoom(1, 1, 10),
                new ClassRoom(2, 2, 20));

        //when
        List<ClassRoom> actual = classRoomDao.findAll();

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenReturnClassRoomByIdWhenDeliverClassRoom() {
        //given
        int id = 1;
        ClassRoom expected = new ClassRoom(id, 1, 10);

        //when
        ClassRoom actual = classRoomDao.findById(id);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenAddClassRoomInDatabseWhenDeliverClassRoom() {
        //given
        Integer expected = ROWS_AFTER_ADD;
        ClassRoom classRoom = new ClassRoom(3, 30);

        //when
        classRoomDao.add(classRoom);
        Integer actual = rowCounter.getRowsClassRoom(jdbcTemplate);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenDeleteClassRoomWhenDeliverClassRoom() {
        //given
        ClassRoom classRoom = new ClassRoom(2, 2, 20);
        Integer expected = ROWS_AFTER_DELETE;

        //when
        classRoomDao.delete(classRoom);
        Integer actual = rowCounter.getRowsClassRoom(jdbcTemplate);

        //then
        assertEquals(expected, actual);
    }

}
