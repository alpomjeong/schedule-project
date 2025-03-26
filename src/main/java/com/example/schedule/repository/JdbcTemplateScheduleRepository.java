package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.entity.Schedule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * JdbcTemplateScheduleRepository는 스케줄 관련 데이터베이스 연산을 처리하는 클래스입니다.
 * JDBC를 이용하여 스케줄을 저장, 조회, 수정 및 삭제하는 기능을 제공합니다.
 * 이 클래스는 {@link ScheduleRepository} 인터페이스를 구현합니다.
 */
@Repository
public class JdbcTemplateScheduleRepository implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

    /**
     * 생성자. 데이터베이스 연결을 위한 JdbcTemplate을 초기화합니다.
     *
     * @param dataSource 데이터베이스 연결을 위한 {@link DataSource}
     */
     public JdbcTemplateScheduleRepository(DataSource dataSource){
         this.jdbcTemplate = new JdbcTemplate(dataSource);
     }

    /**
     * 새 스케줄을 저장합니다.
     *
     * @param schedule 저장할 스케줄 객체
     * @return 저장된 스케줄의 응답 DTO
     */
    @Override
    public ScheduleResponseDto saveSchedule(Schedule schedule) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("todo",schedule.getTodo());
        parameters.put("author",schedule.getAuthor());
        parameters.put("password",schedule.getPassword());
        parameters.put("created_at",DatetimeConverter());
        parameters.put("updated_at",DatetimeConverter());



        Number key = jdbcInsert.executeAndReturnKey((new MapSqlParameterSource(parameters)));
        return new ScheduleResponseDto(key.longValue(), schedule.getTodo(),schedule.getAuthor(),schedule.getPassword(),DatetimeConverter(),DatetimeConverter());
    }

    /**
     * 모든 스케줄을 조회합니다.
     *
     * @param author 스케줄 작성자 (optional)
     * @param updatedDate 스케줄 수정 날짜 (optional)
     * @return 조건에 맞는 스케줄 목록
     */
    @Override
    public List<ScheduleResponseDto> findAllSchedules(String author,String updatedDate) {
        String sql = "SELECT * FROM schedule WHERE 1=1"; // 기본 쿼리
        List<Object> params = new ArrayList<>();

        if (author != null) {
            sql += " AND author = ?";
            params.add(author);
        }

        if (updatedDate != null) {
            sql += " AND DATE(updated_at) = ?";
            params.add(updatedDate);
        }

        return jdbcTemplate.query(sql, params.toArray(), scheduleRowMapper());
    }


    /**
     * ID로 스케줄을 조회합니다. 스케줄이 없으면 예외를 발생시킵니다.
     *
     * @param id 조회할 스케줄의 ID
     * @return 조회된 스케줄의 응답 DTO
     * @throws ResponseStatusException 스케줄을 찾을 수 없을 때 발생
     */
    @Override
    public ScheduleResponseDto findScheduleByIdOrElseThrow(Long id) {
        List<ScheduleResponseDto> result = jdbcTemplate.query("select * from schedule where id = ?", scheduleRowMapper2(), id);
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id));
    }

    /**
     * ID로 스케줄의 비밀번호를 조회합니다.
     *
     * @param id 조회할 스케줄의 ID
     * @return 해당 스케줄의 비밀번호 목록
     */
    @Override
    public List<String> getPasswordById(Long id) {
        return jdbcTemplate.query("select password from schedule where id = ?",(rs,i) -> rs.getString("password"),id);
    }

    /**
     * ID로 스케줄을 삭제합니다.
     *
     * @param id 삭제할 스케줄의 ID
     */
    @Override
    public void deleteSchedule(Long id) {
         String password = getPasswordById(id).toString();
         jdbcTemplate.update("delete from schedule where id = ?",id);
    }

    /**
     * 스케줄을 수정합니다.
     *
     * @param id 수정할 스케줄의 ID
     * @param author 새로운 작성자 이름
     * @param todo 새로운 스케줄 내용
     * @return 수정된 스케줄의 응답 DTO
     */
    @Override
    public ResponseEntity<ScheduleResponseDto> updateSchedule(Long id, String author,String todo) {
        String password =getPasswordById(id).toString();
         jdbcTemplate.update("UPDATE schedule SET author = ?, todo = ? WHERE id = ?;",author,todo,id);
         return new ResponseEntity<>(findScheduleByIdOrElseThrow(id),HttpStatus.OK);
    }


    /**
     * 현재 날짜와 시간을 "yyyy-MM-dd HH:mm:ss" 형식으로 반환합니다.
     *
     * @return 현재 날짜와 시간 문자열
     */
    public String DatetimeConverter(){
         return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    }

    /**
     * Schedule 객체의 결과를 매핑하는 RowMapper를 반환합니다.
     *
     * @return {@link ScheduleResponseDto}를 반환하는 {@link RowMapper}
     */
    private RowMapper<ScheduleResponseDto> scheduleRowMapper() {
         return new RowMapper<ScheduleResponseDto>() {
             @Override
             public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                 return new ScheduleResponseDto(
                         rs.getLong("id"),
                         rs.getString("todo"),
                         rs.getString("author"),
                         rs.getString("created_at"),
                         rs.getString("updated_at")
                 );
             }


         };
    }

    /**
     * Schedule 객체의 결과를 매핑하는 RowMapper를 반환합니다.
     *
     * @return {@link ScheduleResponseDto}를 반환하는 {@link RowMapper}
     */
    private RowMapper<ScheduleResponseDto> scheduleRowMapper2() {
        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("todo"),
                        rs.getString("author"),
                        rs.getString("created_at"),
                        rs.getString("updated_at")
                );
            }


        };
    }
}