package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ScheduleService {

    ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto);

    List<ScheduleResponseDto> findAllSchedule(String author,String updated_at);


    ScheduleResponseDto findScheduleById(Long id);

    ResponseEntity<String> deleteSchedule(Long id, ScheduleRequestDto scheduleRequestDto);

    List<String> getPasswordById(Long id);

   ResponseEntity<ScheduleResponseDto> updateSchedule(Long id, ScheduleRequestDto scheduleRequestDto);
}

