package ua.com.foxminded.university.model;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TimeLesson {

    private int id;
    private LocalDateTime times;

    public TimeLesson(LocalDateTime time) {
        this.times = time;
    }

    public TimeLesson(int id, LocalDateTime time) {
        this.id = id;
        this.times = time;
    }

    public TimeLesson() {
    }

}
