package com.example.schedule.dto;


import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private String todo;
    private String author;
    private String password;
    private String created_at;
    private String updated_at;

}
