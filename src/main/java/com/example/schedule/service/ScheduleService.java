package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * {@link ScheduleService}는 스케줄 관련 비즈니스 로직을 처리하는 서비스 인터페이스입니다.
 * 이 인터페이스는 스케줄을 생성, 조회, 수정, 삭제하는 기능을 정의합니다.
 */
public interface ScheduleService {

    /**
     * 새로운 스케줄을 저장합니다.
     *
     * @param requestDto 저장할 스케줄 정보가 담긴 DTO
     * @return 저장된 스케줄의 응답 DTO
     */
    ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto);

    /**
     * 작성자와 수정 날짜를 기준으로 스케줄 목록을 조회합니다.
     *
     * @param author 작성자 (optional)
     * @param updated_at 수정 날짜 (optional)
     * @return 조건에 맞는 스케줄 목록
     */
    List<ScheduleResponseDto> findAllSchedule(String author,String updated_at);

    /**
     * ID로 스케줄을 조회합니다.
     *
     * @param id 조회할 스케줄의 ID
     * @return 조회된 스케줄의 응답 DTO
     */
    ScheduleResponseDto findScheduleById(Long id);

    /**
     * 스케줄을 삭제합니다.
     *
     * @param id 삭제할 스케줄의 ID
     * @param scheduleRequestDto 삭제할 스케줄의 요청 DTO
     * @return 삭제된 스케줄에 대한 상태 메시지
     */
    ResponseEntity<String> deleteSchedule(Long id, ScheduleRequestDto scheduleRequestDto);

    /**
     * ID로 스케줄의 비밀번호를 조회합니다.
     *
     * @param id 조회할 스케줄의 ID
     * @return 해당 스케줄의 비밀번호 목록
     */
    List<String> getPasswordById(Long id);

    /**
     * 스케줄을 수정합니다.
     *
     * @param id 수정할 스케줄의 ID
     * @param scheduleRequestDto 수정할 스케줄의 정보가 담긴 DTO
     * @return 수정된 스케줄의 응답 DTO
     */
   ResponseEntity<ScheduleResponseDto> updateSchedule(Long id, ScheduleRequestDto scheduleRequestDto);
}

