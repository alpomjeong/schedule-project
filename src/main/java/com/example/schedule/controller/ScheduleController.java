package com.example.schedule.controller;


import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    private  final ScheduleService scheduleService;

    public  ScheduleController(ScheduleService scheduleService){this.scheduleService = scheduleService;}

    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createMemo(@RequestBody ScheduleRequestDto requestDto){
        return new ResponseEntity<>(scheduleService.saveSchedule(requestDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<ScheduleResponseDto> findAllSchedules(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String updatedDate)
    {
        return scheduleService.findAllSchedule(author,updatedDate);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findMemoById(@PathVariable Long id) {

        return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);
    }

}
