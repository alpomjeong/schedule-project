package com.example.schedule.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.cglib.core.Local;

import java.sql.Timestamp;
import java.time.LocalDateTime;


/**
 * Schedule은 스케줄의 기본 엔티티 클래스입니다.
 * 이 클래스는 스케줄의 고유 ID, 내용(todo), 작성자(author), 비밀번호(password), 생성 시간(created_at), 수정 시간(updated_at)을 저장합니다.
 * 데이터베이스에서 스케줄 관련 정보를 관리하는 데 사용됩니다.
 */

@Getter
@AllArgsConstructor
public class Schedule {

    /**
     * 스케줄의 고유 ID입니다.
     * 각 스케줄을 구별하는 데 사용됩니다.
     */
    private long id;

    /**
     * 스케줄의 내용입니다.
     * 예: "회의", "기술 스터디" 등 스케줄에 대한 설명을 나타냅니다.
     */
    private String todo;

    /**
     * 스케줄 작성자의 이름입니다.
     * 이 필드는 스케줄을 작성한 사람을 식별하는 데 사용됩니다.
     */
    private String author;

    /**
     * 스케줄 수정 및 삭제를 위한 비밀번호입니다.
     * 보안상의 이유로 특정 스케줄을 수정하거나 삭제할 때 사용됩니다.
     */
    private String password;

    /**
     * 스케줄의 생성 시간입니다.
     * 스케줄이 처음 만들어진 시간을 나타내며, 날짜와 시간을 포함합니다.
     */
    private String created_at;

    /**
     * 스케줄의 수정 시간입니다.
     * 스케줄이 수정된 시간을 나타내며, 날짜와 시간을 포함합니다.
     */
    private String updated_at;

}
