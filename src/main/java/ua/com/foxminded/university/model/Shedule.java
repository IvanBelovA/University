package ua.com.foxminded.university.model;

import lombok.Data;

@Data
public class Shedule {

    private int id;
    private Lesson lesson;
    private Course course;
    private Group group;
    private Teacher teacher;
    private ClassRoom classRoom;

    public Shedule(Lesson lesson, Course course, Group group, Teacher teacher, ClassRoom classRoom) {
        this.lesson = lesson;
        this.course = course;
        this.group = group;
        this.teacher = teacher;
        this.classRoom = classRoom;
    }

    public Shedule() {}

}
