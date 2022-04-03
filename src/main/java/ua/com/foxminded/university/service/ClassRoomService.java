package ua.com.foxminded.university.service;

import java.util.List;

import ua.com.foxminded.university.model.ClassRoom;

public interface ClassRoomService {

    List<ClassRoom> findAll();
    ClassRoom findById(int id);
    void add(ClassRoom classRoom);
    void delete(ClassRoom classRoom);
}
