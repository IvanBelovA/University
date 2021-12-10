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
import ua.com.foxminded.university.dao.impl.SheduleDaoImpl;
import ua.com.foxminded.university.dto.SheduleDto;

@SpringBootTest(classes = TestConfiguration.class)
class SheduleDaoImplTest {

    private static final Integer ROWS_AFTER_ADD = 3;
    private static final Integer ROWS_AFTER_DELETE = 1;

    private RowCounter rowCounter;
    private TestConfiguration configuration;
    private AnnotationConfigApplicationContext context;
    private SheduleDaoImpl sheduleDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setUp() {
        rowCounter = new RowCounter();
        configuration = new TestConfiguration();
        context = configuration.getContext();
        sheduleDao = new SheduleDaoImpl(jdbcTemplate);
    }

    @AfterEach
    void closeUp() {
        context.close();
    }

    @Test
    void givenReturnListWithAllSheduleDtoWhenCallHim() {
        //given
        List<SheduleDto> expected = Arrays.asList(new SheduleDto(1, 1, 1, 1 , 1, 1), new SheduleDto(2, 2, 2, 2 , 2, 2));

        //when
        List<SheduleDto> actual = sheduleDao.findAll();

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenReturnSheduleDtoByIdWhenDeliverGroup() {
        //given
        int id = 1;
        SheduleDto expected = new SheduleDto(id, 1, 1, 1 , 1, 1);

        //when
        SheduleDto actual = sheduleDao.findById(id);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenAddSheduleDtoInDatabseWhenDeliverSheduleDto() {
        //given
        Integer expected = ROWS_AFTER_ADD;
        SheduleDto sheduleDto = new SheduleDto(3, 2, 2, 2, 2, 2);

        //when
        sheduleDao.add(sheduleDto);
        Integer actual = rowCounter.getRowsShedule(jdbcTemplate);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenDeleteSheduleWhenDeliverSheduleDto() {
        //given
        Integer expected = ROWS_AFTER_DELETE;
        SheduleDto sheduleDto = new SheduleDto(1, 1, 1, 1, 1, 1);

        //when
        sheduleDao.delete(sheduleDto);
        Integer actual = rowCounter.getRowsShedule(jdbcTemplate);

        //then
        assertEquals(expected, actual);
    }

}
