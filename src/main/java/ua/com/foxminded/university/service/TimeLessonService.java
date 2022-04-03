package ua.com.foxminded.university.service;

import java.util.List;

import ua.com.foxminded.university.model.TimeLesson;

public interface TimeLessonService {

    List<TimeLesson> findAll();
    TimeLesson findById(int id);
    void add(TimeLesson timeLesson);
    void delete(TimeLesson timeLesson);
}
