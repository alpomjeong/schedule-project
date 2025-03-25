package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto);

    List<ScheduleResponseDto> findAllSchedules(String author, String updatedDate);

    Optional<ScheduleResponseDto> findScheduleById(Long id);

    ScheduleResponseDto findScheduleByIdOrElseThrow(Long id);
}
