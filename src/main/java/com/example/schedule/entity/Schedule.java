package com.example.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Schedule {
    private long id;
    private String todo;
    private String author;
    private String password;
    private String created_at;
    private String updated_at;

    public Schedule(String todo, String author, String password, String created_at, String updated_at) {
        this.todo = todo;
        this.author = author;
        this.password = password;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }
}
