package com.example.schedule.controller;


import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * ScheduleController는 스케줄 관련 API를 처리하는 컨트롤러 클래스입니다.
 * 이 클래스는 일정의 생성, 조회, 수정, 삭제 기능을 제공합니다.
 */
@RestController
@RequestMapping("/schedules")
public class ScheduleController {
    private  final ScheduleService scheduleService;

    /**
     * ScheduleController의 생성자입니다.
     *
     * @param scheduleService ScheduleService 객체를 주입합니다.
     */
    public  ScheduleController(ScheduleService scheduleService){this.scheduleService = scheduleService;}

    /**
     * 새로운 스케줄을 생성합니다.
     *
     * @param requestDto 생성할 스케줄의 요청 데이터
     * @return 생성된 스케줄 정보를 담은 ResponseEntity
     */
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createMemo(@RequestBody ScheduleRequestDto requestDto){
        return new ResponseEntity<>(scheduleService.saveSchedule(requestDto), HttpStatus.CREATED);
    }

    /**
     * 모든 스케줄을 조회합니다. 선택적으로 작성자(author)와 수정일(updatedDate)을 필터로 사용할 수 있습니다.
     *
     * @param author 작성자의 이름
     * @param updatedDate 수정된 날짜
     * @return 필터링된 모든 스케줄 리스트
     */
    @GetMapping
    public List<ScheduleResponseDto> findAllSchedules(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String updatedDate)
    {
        return scheduleService.findAllSchedule(author,updatedDate);
    }

    /**
     * 주어진 ID에 해당하는 스케줄을 조회합니다.
     *
     * @param id 조회할 스케줄의 ID
     * @return 해당 ID의 스케줄 정보를 담은 ResponseEntity
     */
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findMemoById(@PathVariable Long id) {

        return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);
    }


    /**
     * 주어진 ID에 해당하는 스케줄을 삭제합니다. 요청 본문에 비밀번호를 포함해야 하며, 비밀번호가 맞는지 확인 후 삭제합니다.
     *
     * @param requestDto 삭제 요청 시 제공된 비밀번호를 포함한 스케줄 데이터
     * @param id 삭제할 스케줄의 ID
     * @return 삭제 성공 여부를 나타내는 ResponseEntity
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteScheduleById(@RequestBody ScheduleRequestDto requestDto, @PathVariable Long id){
        return scheduleService.deleteSchedule(id, requestDto);
    }

    /**
     * 주어진 ID에 해당하는 스케줄을 수정합니다. 수정할 스케줄의 데이터는 요청 본문에 포함됩니다.
     *
     * @param requestDto 수정할 스케줄 데이터
     * @param id 수정할 스케줄의 ID
     * @return 수정된 스케줄 정보를 담은 ResponseEntity
     */
    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@RequestBody ScheduleRequestDto requestDto,@PathVariable Long id){
        return scheduleService.updateSchedule(id,requestDto);
    }

}
