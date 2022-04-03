package ua.com.foxminded.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ua.com.foxminded.university.dao.ClassRoomDao;
import ua.com.foxminded.university.model.ClassRoom;

@Slf4j
@Service
public class ClassRoomServiceImpl {

    private ClassRoomDao classRoomDao;

    @Autowired
    public ClassRoomServiceImpl(ClassRoomDao classRoomDao) {
        this.classRoomDao = classRoomDao;
    }

    public List<ClassRoom> findAll() {
        log.debug("Finding all class room");
        return classRoomDao.findAll();
    }

    public ClassRoom findById(int id) {
        log.debug("Find class room with id = {}", id);
        return classRoomDao.findById(id);
    }

    public void add(ClassRoom classRoom) {
        log.debug("Add class room with number - {}, capacity - {}",
                classRoom.getNumber(), classRoom.getCapacity());
        classRoomDao.add(classRoom);
    }

    public void delete(ClassRoom classRoom) {
        log.debug("Delete class room with id = {}", classRoom.getId());
        classRoomDao.delete(classRoom);
    }

}
