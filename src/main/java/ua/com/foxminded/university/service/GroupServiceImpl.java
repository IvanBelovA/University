package ua.com.foxminded.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.dao.GroupDao;
import ua.com.foxminded.university.model.Group;

@Service
public class GroupServiceImpl {

    private GroupDao groupDao;

    @Autowired
    public GroupServiceImpl(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public List<Group> findAll() {
        return groupDao.findAll();
    }

    public Group findById(int id) {
        return groupDao.findById(id);
    }

    public void add(Group group) {
        groupDao.add(group);
    }

    public void delete(Group group) {
        groupDao.delete(group);
    }

    public Group findGroupStudents(Group group) {
        return groupDao.findGroupStudents(group);
    }

}
