package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import com.example.schedule.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@link ScheduleServiceImpl}은 {@link ScheduleService} 인터페이스를 구현한 서비스 클래스입니다.
 * 이 클래스는 스케줄 관련 비즈니스 로직을 처리하며, {@link ScheduleRepository}와 상호작용하여 데이터를 관리합니다.
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    /**
     * 생성자 주입을 통해 {@link ScheduleRepository}를 초기화합니다.
     *
     * @param scheduleRepository 스케줄 데이터베이스에 접근하기 위한 레포지토리
     */
    public ScheduleServiceImpl(ScheduleRepository scheduleRepository){this.scheduleRepository = scheduleRepository; }

    /**
     * 새로운 스케줄을 저장합니다.
     *
     * @param requestDto 저장할 스케줄의 요청 DTO
     * @return 저장된 스케줄의 응답 DTO
     */
    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto requestDto) {

        Schedule schedule = new Schedule(0,requestDto.getTodo(), requestDto.getAuthor(),requestDto.getPassword(),"","");
        return scheduleRepository.saveSchedule(schedule);
    }

    /**
     * 작성자와 수정 날짜를 기준으로 스케줄 목록을 조회합니다.
     *
     * @param author 작성자 (optional)
     * @param updatedDate 수정 날짜 (optional)
     * @return 조건에 맞는 스케줄 목록
     */
    @Override
    public List<ScheduleResponseDto> findAllSchedule(String author,String updatedDate) {
        List<ScheduleResponseDto> allSchedules = scheduleRepository.findAllSchedules(author,updatedDate);
        return allSchedules;
    }

    /**
     * ID로 스케줄을 조회합니다.
     *
     * @param id 조회할 스케줄의 ID
     * @return 조회된 스케줄의 응답 DTO
     */
    @Override
    public ScheduleResponseDto findScheduleById(Long id){
        // ScheduleResponseDto로 변환 후 반환
        return scheduleRepository.findScheduleByIdOrElseThrow(id);
    }

    /**
     * 스케줄을 삭제합니다.
     * 비밀번호가 일치하는 경우에만 삭제를 허용합니다.
     *
     * @param id 삭제할 스케줄의 ID
     * @param scheduleRequestDto 삭제할 스케줄의 요청 DTO
     * @return 삭제 성공 또는 실패 메시지
     */
    @Override
    public ResponseEntity<String> deleteSchedule(Long id, ScheduleRequestDto scheduleRequestDto) {


        if(getPasswordById(id).get(0).equals(scheduleRequestDto.getPassword())) {
           scheduleRepository.deleteSchedule(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Schedule deleted successfully");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("비번틀림");


        }


    }

    /**
     * 스케줄 ID로 비밀번호를 조회합니다.
     *
     * @param id 조회할 스케줄의 ID
     * @return 해당 스케줄의 비밀번호 목록
     */
    @Override
    public List<String> getPasswordById(Long id) {
       return scheduleRepository.getPasswordById(id);

    }

    /**
     * 스케줄을 수정합니다.
     *
     * @param id 수정할 스케줄의 ID
     * @param scheduleRequestDto 수정할 스케줄의 요청 DTO
     * @return 수정된 스케줄의 응답 DTO
     */
    @Override
    public ResponseEntity<ScheduleResponseDto> updateSchedule(Long id, ScheduleRequestDto scheduleRequestDto) {
        return scheduleRepository.updateSchedule(id,scheduleRequestDto.getTodo(),scheduleRequestDto.getAuthor() );
    }


}
