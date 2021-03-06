package ua.com.foxminded.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import ua.com.foxminded.university.dao.GroupDao;
import ua.com.foxminded.university.model.Group;
import ua.com.foxminded.university.service.impl.GroupServiceImpl;

@ExtendWith(SpringExtension.class)
public class GroupServiceTest {

    @Mock
    private GroupDao groupDao;

    @InjectMocks
    private GroupServiceImpl groupService;

    @Test
    void givenReturnListWithGroupsWhenCallHim() {
        //given
        List<Group> expected = Arrays.asList(new Group(1, "AA-11"), new Group(2, "BB-22"));
        //when
        when(groupDao.findAll()).thenReturn(expected);
        List<Group> actual = groupService.findAll();

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenReturnGroupWhenDeliverId() {
        //given
        int id = 1;
        Group expected = new Group(id, "AA-11");

        //when
        when(groupDao.findById(id)).thenReturn(expected);
        Group actual = groupService.findById(id);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenCallAddMthodOneTimesWhenDeliverGroup() {
        //given
        GroupServiceImpl groupService = Mockito.mock(GroupServiceImpl.class); 
        Group group = new Group(1, "AA-11");

        //when
        groupService.add(group);

        //then
        verify(groupService, times(1)).add(group);
    }

    @Test
    void givenCallDeleteMthodOneTimesWhenDeliverGroup() {
        //given
        GroupServiceImpl groupService = Mockito.mock(GroupServiceImpl.class); 
        Group group = new Group(1, "AA-11");

        //when
        groupService.delete(group);

        //then
        verify(groupService, times(1)).delete(group);
    }

}
