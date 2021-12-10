package ua.com.foxminded.university.model;

import lombok.Data;

@Data
public class ClassRoom {

    private int id;
    private int number;
    private int capacity;

    public ClassRoom(int number, int capacity) {
        this.number = number;
        this.capacity = capacity;
    }

    public ClassRoom() {
    }

    public ClassRoom(int id, int number, int capacity) {
        this.id = id;
        this.number = number;
        this.capacity = capacity;
    }

}
