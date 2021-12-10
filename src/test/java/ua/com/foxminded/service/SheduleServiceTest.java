package ua.com.foxminded.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import ua.com.foxminded.university.dao.ClassRoomDao;
import ua.com.foxminded.university.dao.CourseDao;
import ua.com.foxminded.university.dao.GroupDao;
import ua.com.foxminded.university.dao.LessonDao;
import ua.com.foxminded.university.dao.SheduleDao;
import ua.com.foxminded.university.dao.StudentDao;
import ua.com.foxminded.university.dao.TeacherDao;
import ua.com.foxminded.university.dto.SheduleDto;
import ua.com.foxminded.university.model.ClassRoom;
import ua.com.foxminded.university.model.Course;
import ua.com.foxminded.university.model.Group;
import ua.com.foxminded.university.model.Lesson;
import ua.com.foxminded.university.model.Shedule;
import ua.com.foxminded.university.model.Student;
import ua.com.foxminded.university.model.Teacher;
import ua.com.foxminded.university.service.SheduleServiceImpl;

class SheduleServiceTest {

    private final static int ID_FIRST = 1;
    private final static int ID_SECOND = 2;

    private SheduleServiceImpl sheduleService;
    private SheduleDao sheduleDao;
    private LessonDao lessonDao;
    private GroupDao groupDao;
    private CourseDao courseDao;
    private TeacherDao teacherDao;
    private ClassRoomDao classRoomDao;
    private StudentDao studentDao;

    private List<SheduleDto> listDto;
    private SheduleDto dto;

    @BeforeEach
    void setUp() {
        sheduleDao = Mockito.mock(SheduleDao.class);
        classRoomDao = Mockito.mock(ClassRoomDao.class);
        courseDao = Mockito.mock(CourseDao.class);
        groupDao = Mockito.mock(GroupDao.class);
        lessonDao = Mockito.mock(LessonDao.class);
        teacherDao = Mockito.mock(TeacherDao.class);
        studentDao = Mockito.mock(StudentDao.class);

        sheduleService = new SheduleServiceImpl
                (studentDao, sheduleDao, lessonDao, groupDao, courseDao, teacherDao, classRoomDao);

        listDto = Arrays.asList(new SheduleDto(1, 1, 1, 1, 1, 1),  new SheduleDto(2, 2, 2, 2, 2, 2));
        dto = new SheduleDto(1, 1, 1, 1, 1, 1);

        when(lessonDao.findById(ID_FIRST)).thenReturn(new Lesson(1, 1));
        when(courseDao.findById(ID_FIRST)).thenReturn(new Course(1, "math"));
        when(groupDao.findById(ID_FIRST)).thenReturn(new Group(1, "AA-11"));
        when(teacherDao.findById(ID_FIRST)).thenReturn(new Teacher(1, "Ivan", "Petrov"));
        when(classRoomDao.findById(ID_FIRST)).thenReturn(new ClassRoom(1, 1, 10));

        when(lessonDao.findById(ID_SECOND)).thenReturn(new Lesson(2, 2));
        when(courseDao.findById(ID_SECOND)).thenReturn(new Course(2, "biology"));
        when(groupDao.findById(ID_SECOND)).thenReturn(new Group(2, "BB-22"));
        when(teacherDao.findById(ID_SECOND)).thenReturn(new Teacher(2, "Petr", "Ivanov"));
        when(classRoomDao.findById(ID_SECOND)).thenReturn(new ClassRoom(2, 2, 20));
    }

    @Test
    void givenReturnListWithAllSheduleWhenCallHim() {
        //given
        List<Shedule> shedule = Arrays.asList(
                new Shedule(new Lesson(1, 1), new Course(1, "math"),new Group(1, "AA-11"),
                        new Teacher(1, "Ivan", "Petrov"), new ClassRoom(1, 1, 10)),
                new Shedule(new Lesson(2, 2), new Course(2, "biology"),new Group(2, "BB-22"),
                        new Teacher(2, "Petr", "Ivanov"), new ClassRoom(2, 2, 20)));

        //when
        when(sheduleDao.findAll()).thenReturn(listDto);
        List<Shedule> actual = sheduleService.findAll();

        //then
        assertEquals(shedule, actual);
    }

    @Test
    void givenReturnSheduleWhenDeliverIdShedule() {
        //given
        Shedule expected = new Shedule(new Lesson(1, 1), new Course(1, "math"),
                new Group(1, "AA-11"), new Teacher(1, "Ivan", "Petrov"), new ClassRoom(1, 1, 10));

        //when
        when(sheduleDao.findById(ID_FIRST)).thenReturn(dto);
        Shedule actual = sheduleService.findById(dto);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenCallMthodDaoAddOneTimeWhenDeliverSheduleDto() {
        //given
        SheduleServiceImpl sheduleServiceImpl = Mockito.mock(SheduleServiceImpl.class);

        //when
        sheduleServiceImpl.add(dto);

        //then
        verify(sheduleServiceImpl, times(1)).add(dto);
    }

    @Test
    void givenCallMthodDaoDeleteOneTimeWhenDeliverSheduleDto() {
        //given
        SheduleServiceImpl sheduleServiceImpl = Mockito.mock(SheduleServiceImpl.class);

        //when
        sheduleServiceImpl.add(dto);

        //then
        verify(sheduleServiceImpl, times(1)).add(dto);
    }

    @Test
    void givenReturnListSheduleRelatedWithTeacherWhenDeliverTeacher() {
        //given
        Teacher teacher = new Teacher(2, "Petr", "Ivanov");
        List<Shedule> expected = Arrays.asList(new Shedule(new Lesson(2, 2), new Course(2, "biology"),new Group(2, "BB-22"), 
                new Teacher(2, "Petr", "Ivanov"), new ClassRoom(2, 2, 20)));

        //when
        when(sheduleDao.findAll()).thenReturn(listDto);
        List<Shedule> actual = sheduleService.getSheduleTeacher(teacher);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void givenReturnListSheduleRelatedWithStudentWhenDeliverStudent() {
        //given
        List<Course> courses = Arrays.asList(new Course(1, "math"));
        List<Shedule> expected = Arrays.asList(
                new Shedule(new Lesson(1, 1), new Course(1, "math"),new Group(1, "AA-11"),
                        new Teacher(1, "Ivan", "Petrov"), new ClassRoom(1, 1, 10)));

        Student student = new Student(1, "Ivan", "Ivanov");
        student.setCourses(courses);

        //when
        when(sheduleDao.findAll()).thenReturn(listDto);
        when(studentDao.findCoursesStudent(student)).thenReturn(student);
        List<Shedule> actual = sheduleService.getSheduleStudent(student);

        //then
        assertEquals(expected, actual);
    }

}
