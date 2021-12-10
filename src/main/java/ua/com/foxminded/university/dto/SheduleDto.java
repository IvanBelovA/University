package ua.com.foxminded.university.dto;

import lombok.Data;

@Data
public class SheduleDto {

    private int id;
    private int lessonId;
    private int groupId;
    private int courseId;
    private int teacherId;
    private int classroomId;

    public SheduleDto(int id, int lessonId, int groupId, int courseId, int teacherId, int classRoomId) {
        this.id = id;
        this.lessonId = lessonId;
        this.groupId = groupId;
        this.courseId = courseId;
        this.teacherId = teacherId;
        this.classroomId = classRoomId;
    }

    public SheduleDto() {
    }

}
