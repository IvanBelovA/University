package ua.com.foxminded.university.service;

import java.util.List;

import ua.com.foxminded.university.dto.SheduleDto;
import ua.com.foxminded.university.model.Shedule;
import ua.com.foxminded.university.model.Student;
import ua.com.foxminded.university.model.Teacher;

public interface SheduleService {

    List<Shedule> findAll();
    Shedule findById(SheduleDto dto);
    void add(SheduleDto dto);
    void delete(SheduleDto dto);
    List<Shedule> getSheduleTeacher(Teacher teacher);
    List<Shedule> getSheduleStudent(Student student);
}
