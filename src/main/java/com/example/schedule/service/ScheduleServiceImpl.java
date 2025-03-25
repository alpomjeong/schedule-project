package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository){this.scheduleRepository = scheduleRepository; }
    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto) {


        return scheduleRepository.saveSchedule(requestDto);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedule(String author,String updatedDate) {
        List<ScheduleResponseDto> allSchedules = scheduleRepository.findAllSchedules(author,updatedDate);
        return allSchedules;
    }


    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        // Schedule 엔티티를 DB에서 조회
        ScheduleResponseDto schedule = scheduleRepository.findScheduleByIdOrElseThrow(id);

        // ScheduleResponseDto로 변환 후 반환
        return new ScheduleResponseDto(
                schedule.getId(),
                schedule.getTodo(),
                schedule.getAuthor(),
                schedule.getCreated_at().toString(),  // Timestamp를 String으로 변환
                schedule.getUpdated_at().toString()   // Timestamp를 String으로 변환
        );
    }
}
