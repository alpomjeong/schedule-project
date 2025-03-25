package com.example.schedule.dto;

import com.example.schedule.entity.Schedule;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScheduleResponseDto {
    private long id;
    private String todo;
    private String author;
    private String password;

    private String created_at;
    private String updated_at;

    // password 없는 생성자 추가
    public ScheduleResponseDto(Long id, String todo, String author, String created_at,String updated_at) {
        this.id = id;
        this.todo = todo;
        this.author = author;
        this.updated_at = updated_at;
        this.created_at = created_at;
    }
}


