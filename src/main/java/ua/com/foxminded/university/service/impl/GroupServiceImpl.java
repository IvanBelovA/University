package ua.com.foxminded.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ua.com.foxminded.university.dao.GroupDao;
import ua.com.foxminded.university.model.Group;

@Slf4j
@Service
public class GroupServiceImpl {

    private GroupDao groupDao;

    @Autowired
    public GroupServiceImpl(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public List<Group> findAll() {
        log.debug("Find all groups");
        return groupDao.findAll();
    }

    public Group findById(int id) {
        log.debug("Find group with id = {}", id);
        return groupDao.findById(id);
    }

    public void add(Group group) {
        log.debug("Add group with name - {}", group.getGroupName());
        groupDao.add(group);
    }

    public void delete(Group group) {
        log.debug("Delete group with id = {}", group.getId());
        groupDao.delete(group);
    }

    public Group findGroupStudents(Group group) {
        log.debug("Fing all students related group with id = {}", group.getId());
        return groupDao.findGroupStudents(group);
    }

}
