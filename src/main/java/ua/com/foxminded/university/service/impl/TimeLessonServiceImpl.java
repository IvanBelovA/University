package ua.com.foxminded.university.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ua.com.foxminded.university.dao.TimeLessonDao;
import ua.com.foxminded.university.model.TimeLesson;
import ua.com.foxminded.university.service.TimeLessonService;

@Slf4j
@Service
public class TimeLessonServiceImpl implements TimeLessonService{

    private TimeLessonDao timeLessonDao;

    @Autowired
    public TimeLessonServiceImpl(TimeLessonDao timeLessonDao) {
        this.timeLessonDao = timeLessonDao;
    }

    public List<TimeLesson> findAll() {
        log.debug("Find all time lessons");
        return timeLessonDao.findAll();
    }

    public TimeLesson findById(int id) {
        log.debug("Find time lesson with id = {}", id);
        return timeLessonDao.findById(id);
    }

    public void add(TimeLesson timeLesson) {
        log.debug("Add time lesson");
        timeLessonDao.add(timeLesson);
    }

    public void delete(TimeLesson timeLesson) {
        log.debug("Delete time lesson");
        timeLessonDao.delete(timeLesson);
    }

}
