package ua.com.foxminded.university.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ua.com.foxminded.university.dao.ClassRoomDao;
import ua.com.foxminded.university.dao.CourseDao;
import ua.com.foxminded.university.dao.GroupDao;
import ua.com.foxminded.university.dao.LessonDao;
import ua.com.foxminded.university.dao.SheduleDao;
import ua.com.foxminded.university.dao.StudentDao;
import ua.com.foxminded.university.dao.TeacherDao;
import ua.com.foxminded.university.dto.SheduleDto;
import ua.com.foxminded.university.exception.DaoException;
import ua.com.foxminded.university.exception.ServiceException;
import ua.com.foxminded.university.model.Course;
import ua.com.foxminded.university.model.Shedule;
import ua.com.foxminded.university.model.Teacher;
import ua.com.foxminded.university.model.Student;

@Slf4j
@Service
public class SheduleServiceImpl {

    private StudentDao studentDao;
    private SheduleDao sheduleDao;
    private LessonDao lessonDao;
    private GroupDao groupDao;
    private CourseDao courseDao;
    private TeacherDao teacherdao;
    private ClassRoomDao classRoomDao;

    @Autowired
    public SheduleServiceImpl(StudentDao studentDao, SheduleDao sheduleDao, LessonDao lessonDao, GroupDao groupDao,
            CourseDao courseDao, TeacherDao teacherdao, ClassRoomDao classRoomDao) {
        this.studentDao = studentDao;
        this.sheduleDao = sheduleDao;
        this.lessonDao = lessonDao;
        this.groupDao = groupDao;
        this.courseDao = courseDao;
        this.teacherdao = teacherdao;
        this.classRoomDao = classRoomDao;
    }

    public List<Shedule> findAll() {
        log.debug("Find all shedules");
        return sheduleDao.findAll()
                .stream()
                .map(sheduleDto -> new Shedule(
                        lessonDao.findById(sheduleDto.getLessonId()),
                        courseDao.findById(sheduleDto.getCourseId()),
                        groupDao.findById(sheduleDto.getGroupId()),
                        teacherdao.findById(sheduleDto.getTeacherId()),
                        classRoomDao.findById(sheduleDto.getClassroomId())))
                .collect(Collectors.toList());
    }

    public Shedule findById(SheduleDto dto) {
        SheduleDto sheduleDto = sheduleDao.findById(dto.getId());
        log.debug("Find shedule with id = {}", dto.getId());
        return new Shedule(
                lessonDao.findById(sheduleDto.getLessonId()),
                courseDao.findById(sheduleDto.getCourseId()),
                groupDao.findById(sheduleDto.getGroupId()),
                teacherdao.findById(sheduleDto.getTeacherId()),
                classRoomDao.findById(sheduleDto.getClassroomId()));
    }

    public void add(SheduleDto dto) {
        try {
            log.debug("Add shedule");
            sheduleDao.add(dto);
        } catch (DaoException e) {
            throw new ServiceException("Invalid input data");
        }
    }

    public void delete(SheduleDto dto) {
        log.debug("Delete shedule with id = {}", dto.getId());
        sheduleDao.delete(dto);
    }

    public List<Shedule> getSheduleTeacher(Teacher teacher) {
        log.debug("Find all shedules related teacher with id = {}", teacher.getId());
        return sheduleDao.findAll()
                .stream()
                .filter(dto -> dto.getTeacherId() == teacher.getId())
                .map(sheduleDto -> new Shedule(
                        lessonDao.findById(sheduleDto.getLessonId()),
                        courseDao.findById(sheduleDto.getCourseId()),
                        groupDao.findById(sheduleDto.getGroupId()),
                        teacherdao.findById(sheduleDto.getTeacherId()),
                        classRoomDao.findById(sheduleDto.getClassroomId())))
                .collect(Collectors.toList());
    }

    public List<Shedule> getSheduleStudent(Student student) {
        List<Course> courses = studentDao.findCoursesStudent(student).getCourses();
        log.debug("Find shedules related student with id = {}", student.getId());
        return sheduleDao.findAll()
                .stream()
                .filter(dto -> courses
                        .stream()
                        .anyMatch(course -> dto.getCourseId() == course.getId()))
                .map(sheduleDto -> new Shedule(
                        lessonDao.findById(sheduleDto.getLessonId()),
                        courseDao.findById(sheduleDto.getCourseId()),
                        groupDao.findById(sheduleDto.getGroupId()),
                        teacherdao.findById(sheduleDto.getTeacherId()),
                        classRoomDao.findById(sheduleDto.getClassroomId())))
                .collect(Collectors.toList());
    }

}
