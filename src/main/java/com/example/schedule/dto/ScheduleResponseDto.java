package com.example.schedule.dto;

import com.example.schedule.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
    private long id;
    private String todo;
    private String author;
    private String password;
    private String created_at;
    private String updated_at;

    public ScheduleResponseDto(Schedule schedule){
        this.id = schedule.getId();
        this.author=schedule.getAuthor();
        this.password=schedule.getPassword();
        this.created_at=schedule.getCreated_at();
        this.updated_at=schedule.getUpdated_at();
    }

}
