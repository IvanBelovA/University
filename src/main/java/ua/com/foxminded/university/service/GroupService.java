package ua.com.foxminded.university.service;

import java.util.List;

import ua.com.foxminded.university.model.Group;

public interface GroupService {

    List<Group> findAll();
    Group findById(int id);
    void add(Group group);
    void delete(Group group);
    Group findGroupStudents(Group group);
}
