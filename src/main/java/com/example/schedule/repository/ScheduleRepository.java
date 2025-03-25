package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    ScheduleResponseDto saveSchedule(Schedule schedule);

    List<ScheduleResponseDto> findAllSchedules(String author, String updatedDate);

//    Optional<ScheduleResponseDto> findScheduleById(Long id);

    ScheduleResponseDto findScheduleByIdOrElseThrow(Long id);

    List<String> getPasswordById(Long id);

    void deleteSchedule(Long id);

    ResponseEntity<ScheduleResponseDto> updateSchedule(Long id,String author,String todo);


}
