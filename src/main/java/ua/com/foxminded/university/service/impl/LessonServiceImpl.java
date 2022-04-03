package ua.com.foxminded.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ua.com.foxminded.university.dao.LessonDao;
import ua.com.foxminded.university.model.Lesson;

@Slf4j
@Service
public class LessonServiceImpl {

    private LessonDao lessonDao;

    @Autowired
    public LessonServiceImpl(LessonDao lessonDao) {
        this.lessonDao = lessonDao;
    }

    public List<Lesson> findAll() {
        log.debug("Find all lessons");
        return lessonDao.findAll();
    }

    public Lesson findById(int id) {
        log.debug("Find lesson with id = {}", id);
        return lessonDao.findById(id);
    }

    public void add(Lesson lesson) {
        log.debug("Add lesson with number - {}", lesson.getNumber());
        lessonDao.add(lesson);
    }

    public void delete(Lesson lesson) {
        log.debug("Delete lesson with id = {}", lesson.getId());
        lessonDao.delete(lesson);
    }

}
