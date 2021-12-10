package ua.com.foxminded.university.service;

import java.util.List;

import org.springframework.stereotype.Service;

import ua.com.foxminded.university.dao.ClassRoomDao;
import ua.com.foxminded.university.model.ClassRoom;

@Service
public class ClassRoomServiceImpl {

    private ClassRoomDao classRoomDao;

    public ClassRoomServiceImpl(ClassRoomDao classRoomDao) {
        this.classRoomDao = classRoomDao;
    }

    public List<ClassRoom> findAll() {
        return classRoomDao.findAll();
    }

    public ClassRoom findById(int id) {
        return classRoomDao.findById(id);
    }

    public void add(ClassRoom classRoom) {
        classRoomDao.add(classRoom);
    }

    public void delete(ClassRoom classRoom) {
        classRoomDao.delete(classRoom);
    }

}
