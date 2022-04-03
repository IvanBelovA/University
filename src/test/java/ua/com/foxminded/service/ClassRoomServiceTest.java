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

import ua.com.foxminded.university.dao.ClassRoomDao;
import ua.com.foxminded.university.model.ClassRoom;
import ua.com.foxminded.university.service.impl.ClassRoomServiceImpl;

@ExtendWith(SpringExtension.class)
class ClassRoomServiceTest {

    @Mock
    private ClassRoomDao classRoomDao;

    @InjectMocks
    private ClassRoomServiceImpl classRoomService;

    @Test
    void givenReturnListWithClassRoomWhenCallHim() {
        //given
        List<ClassRoom> expected = Arrays.asList(new ClassRoom(1, 1, 10),
                new ClassRoom(2, 2, 20));
        //when
        when(classRoomDao.findAll()).thenReturn(expected);
        List<ClassRoom> actual = classRoomService.findAll();

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenReturnClassRoomWhenDeliverId() {
        //given
        int id = 1;
        ClassRoom expected = new ClassRoom(1, 1, 10);

        //when
        when(classRoomDao.findById(id)).thenReturn(expected);
        ClassRoom actual = classRoomService.findById(id);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenCallAddMthodOneTimesWhenDeliverClassRoom() {
        //given
        ClassRoomServiceImpl classRoomService = Mockito.mock(ClassRoomServiceImpl.class); 
        ClassRoom classRoom = new ClassRoom(1, 1, 10);

        //when
        classRoomService.add(classRoom);

        //then
        verify(classRoomService, times(1)).add(classRoom);
    }

    @Test
    void givenCallDeleteMthodOneTimesWhenDeliverClassRoom() {
        //given
        ClassRoomServiceImpl classRoomService = Mockito.mock(ClassRoomServiceImpl.class); 
        ClassRoom classRoom = new ClassRoom(1, 1, 10);

        //when
        classRoomService.delete(classRoom);

        //then
        verify(classRoomService, times(1)).delete(classRoom);
    }

}
