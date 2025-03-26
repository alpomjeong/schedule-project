package com.example.schedule.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * ScheduleRequestDto는 클라이언트가 스케줄을 생성하거나 수정할 때 사용하는 요청 데이터를 담는 DTO(Data Transfer Object) 클래스입니다.
 * 이 클래스는 일정에 대한 정보(todo, author, password)를 포함합니다.
 */
@Getter
public class ScheduleRequestDto {

    /**
     * 일정의 내용입니다.
     * 예: "회의", "기술 스터디"
     */
    private String todo;

    /**
     * 일정 작성자의 이름입니다.
     */
    private String author;

    /**
     * 일정 수정 또는 삭제를 위한 비밀번호입니다.
     */
    private String password;
}
