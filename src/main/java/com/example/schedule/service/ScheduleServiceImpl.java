package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository){this.scheduleRepository = scheduleRepository; }
    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto) {

        Schedule schedule = new Schedule(0,requestDto.getTodo(), requestDto.getAuthor(),requestDto.getPassword(),"","");
        return scheduleRepository.saveSchedule(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findAllSchedule(String author,String updatedDate) {
        List<ScheduleResponseDto> allSchedules = scheduleRepository.findAllSchedules(author,updatedDate);
        return allSchedules;
    }


    @Override
    public ScheduleResponseDto findScheduleById(Long id){
        // ScheduleResponseDto로 변환 후 반환
        return scheduleRepository.findScheduleByIdOrElseThrow(id);
    }

    @Override
    public ResponseEntity<String> deleteSchedule(Long id, ScheduleRequestDto scheduleRequestDto) {


        if(getPasswordById(id).get(0).equals(scheduleRequestDto.getPassword())) {
           scheduleRepository.deleteSchedule(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Schedule deleted successfully");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("비번틀림");


        }


    }

    @Override
    public List<String> getPasswordById(Long id) {
       return scheduleRepository.getPasswordById(id);

    }

    @Override
    public ResponseEntity<ScheduleResponseDto> updateSchedule(Long id, ScheduleRequestDto scheduleRequestDto) {
        return scheduleRepository.updateSchedule(id,scheduleRequestDto.getTodo(),scheduleRequestDto.getAuthor() );
    }


}
