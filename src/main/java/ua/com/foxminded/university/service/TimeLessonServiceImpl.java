package ua.com.foxminded.university.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.dao.TimeLessonDao;
import ua.com.foxminded.university.model.TimeLesson;

@Service
public class TimeLessonServiceImpl {

    private TimeLessonDao timeLessonDao;

    @Autowired
    public TimeLessonServiceImpl(TimeLessonDao timeLessonDao) {
        this.timeLessonDao = timeLessonDao;
    }

    public List<TimeLesson> findAll() {
        return timeLessonDao.findAll();
    }

    public TimeLesson findById(int id) {
        return timeLessonDao.findById(id);
    }

    public void add(TimeLesson timeLesson) {
        timeLessonDao.add(timeLesson);
    }

    public void delete(TimeLesson timeLesson) {
        timeLessonDao.delete(timeLesson);
    }

}
