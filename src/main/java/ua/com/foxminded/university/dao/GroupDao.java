package ua.com.foxminded.university.dao;

import ua.com.foxminded.university.model.Group;

public interface GroupDao extends GenericDao<Group> {

    Group findGroupStudents(Group group);
}
