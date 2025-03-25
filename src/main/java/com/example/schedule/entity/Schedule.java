package com.example.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Schedule {
    private long id;
    private String todo;
    private String author;
    private String password;
    private String created_at;
    private String updated_at;

}
