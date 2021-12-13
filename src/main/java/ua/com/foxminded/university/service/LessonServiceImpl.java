package ua.com.foxminded.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.dao.LessonDao;
import ua.com.foxminded.university.model.Lesson;

@Service
public class LessonServiceImpl {

    private LessonDao lessonDao;

    @Autowired
    public LessonServiceImpl(LessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

    public List<Lesson> findAll() {
        return lessonDao.findAll();
    }

    public Lesson findById(int id) {
        return lessonDao.findById(id);
    }

    public void add(Lesson lesson) {
        lessonDao.add(lesson);
    }

    public void delete(Lesson lesson) {
        lessonDao.delete(lesson);
    }

}
