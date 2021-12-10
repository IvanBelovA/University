package ua.com.foxminded.university.model;

import lombok.Data;

@Data
public class Lesson {

    private int id;
    private int number;
    private TimeLesson timeLesson;

    public Lesson(int number) {
        this.number = number;
    }

    public Lesson() {}

    public Lesson(int id, int number, TimeLesson timeLesson) {
        this.id = id;
        this.number = number;
        this.timeLesson = timeLesson;
    }

    public Lesson(int id, int number) {
        this.id = id;
        this.number = number;
    }

}
