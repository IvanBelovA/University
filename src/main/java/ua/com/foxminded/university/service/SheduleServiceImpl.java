package ua.com.foxminded.university.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.foxminded.university.dao.ClassRoomDao;
import ua.com.foxminded.university.dao.CourseDao;
import ua.com.foxminded.university.dao.GroupDao;
import ua.com.foxminded.university.dao.LessonDao;
import ua.com.foxminded.university.dao.SheduleDao;
import ua.com.foxminded.university.dao.StudentDao;
import ua.com.foxminded.university.dao.TeacherDao;
import ua.com.foxminded.university.dto.SheduleDto;
import ua.com.foxminded.university.model.Course;
import ua.com.foxminded.university.model.Shedule;
import ua.com.foxminded.university.model.Teacher;
import ua.com.foxminded.university.model.Student;

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
        return new Shedule(
                lessonDao.findById(sheduleDto.getLessonId()),
                courseDao.findById(sheduleDto.getCourseId()),
                groupDao.findById(sheduleDto.getGroupId()),
                teacherdao.findById(sheduleDto.getTeacherId()),
                classRoomDao.findById(sheduleDto.getClassroomId()));
    }

    public void add(SheduleDto dto) {
        sheduleDao.add(dto);
    }

    public void delete(SheduleDto dto) {
        sheduleDao.delete(dto);
    }

    public List<Shedule> getSheduleTeacher(Teacher teacher) {
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
