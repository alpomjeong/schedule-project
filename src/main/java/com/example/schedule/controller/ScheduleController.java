package com.example.schedule.controller;


import com.example.schedule.entity.Schedule;
import com.example.schedule.service.ScheduleService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    private  final ScheduleService scheduleService;

    public  ScheduleController(ScheduleService scheduleService){this.scheduleService = scheduleService;}

}
