package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

/**
 * {@link ScheduleRepository}는 스케줄과 관련된 데이터베이스 연산을 정의하는 인터페이스입니다.
 * 이 인터페이스는 스케줄을 저장, 조회, 수정, 삭제하는 기능을 제공합니다.
 */
public interface ScheduleRepository {

    /**
     * 새 스케줄을 저장합니다.
     *
     * @param schedule 저장할 스케줄 객체
     * @return 저장된 스케줄의 응답 DTO
     */
    ScheduleResponseDto saveSchedule(Schedule schedule);

    /**
     * 작성자와 수정 날짜를 기준으로 스케줄 목록을 조회합니다.
     *
     * @param author 스케줄 작성자 (optional)
     * @param updatedDate 스케줄 수정 날짜 (optional)
     * @return 조건에 맞는 스케줄 목록
     */
    List<ScheduleResponseDto> findAllSchedules(String author, String updatedDate);

    /**
     * ID로 스케줄을 조회하고, 해당 스케줄이 없으면 예외를 발생시킵니다.
     *
     * @param id 조회할 스케줄의 ID
     * @return 조회된 스케줄의 응답 DTO
     * @throws RuntimeException 스케줄을 찾을 수 없을 때 발생
     */
    ScheduleResponseDto findScheduleByIdOrElseThrow(Long id);

    /**
     * ID로 스케줄의 비밀번호를 조회합니다.
     *
     * @param id 조회할 스케줄의 ID
     * @return 해당 스케줄의 비밀번호 목록
     */
    List<String> getPasswordById(Long id);

    /**
     * ID로 스케줄을 삭제합니다.
     *
     * @param id 삭제할 스케줄의 ID
     */
    void deleteSchedule(Long id);

    /**
     * 스케줄을 수정합니다.
     *
     * @param id 수정할 스케줄의 ID
     * @param author 새로운 작성자 이름
     * @param todo 새로운 스케줄 내용
     * @return 수정된 스케줄의 응답 DTO
     */
    ResponseEntity<ScheduleResponseDto> updateSchedule(Long id,String author,String todo);


}
