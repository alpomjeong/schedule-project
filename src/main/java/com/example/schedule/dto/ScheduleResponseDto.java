package com.example.schedule.dto;

import com.example.schedule.entity.Schedule;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ScheduleResponseDto는 스케줄의 응답 데이터를 나타내는 DTO(Data Transfer Object) 클래스입니다.
 * 이 클래스는 클라이언트에게 전달되는 스케줄의 정보(id, todo, author, password, created_at, updated_at)를 포함합니다.
 * 또한 password가 포함되지 않은 생성자도 제공하여 보안상의 이유로 password 필드를 제외할 수 있습니다.
 */
@Getter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ScheduleResponseDto {

    /**
     * 스케줄의 고유 ID입니다.
     */
    private long id;

    /**
     * 스케줄의 내용입니다. 예: "회의", "기술 스터디"
     */
    private String todo;

    /**
     * 스케줄 작성자의 이름입니다.
     */
    private String author;

    /**
     * 스케줄 수정/삭제를 위한 비밀번호입니다. 보안상의 이유로 응답에서 제외될 수 있습니다.
     */
    private String password;

    /**
     * 스케줄 생성 시간입니다.
     */
    private String created_at;

    /**
     * 스케줄 수정 시간입니다.
     */
    private String updated_at;

    /**
     * password가 포함되지 않은 생성자입니다.
     * password 필드를 제외한 생성자를 통해 스케줄 정보를 반환할 수 있습니다.
     * @param id 스케줄의 고유 ID
     * @param todo 스케줄의 내용
     * @param author 스케줄 작성자
     * @param created_at 스케줄 생성 시간
     * @param updated_at 스케줄 수정 시간
     */
    public ScheduleResponseDto(Long id, String todo, String author, String created_at,String updated_at) {
        this.id = id;
        this.todo = todo;
        this.author = author;
        this.updated_at = updated_at;
        this.created_at = created_at;
    }


}


