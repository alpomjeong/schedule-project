package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import org.springframework.http.HttpStatus;
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

@Repository
public class JdbcTemplateScheduleRepository implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

     public JdbcTemplateScheduleRepository(DataSource dataSource){
         this.jdbcTemplate = new JdbcTemplate(dataSource);
     }

    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("todo",scheduleRequestDto.getTodo());
        parameters.put("author",scheduleRequestDto.getAuthor());
        parameters.put("password",scheduleRequestDto.getPassword());
        parameters.put("created_at",DatetimeConverter());
        parameters.put("updated_at",DatetimeConverter());



        Number key = jdbcInsert.executeAndReturnKey((new MapSqlParameterSource(parameters)));
        return new ScheduleResponseDto(key.longValue(), scheduleRequestDto.getTodo(),scheduleRequestDto.getAuthor(),scheduleRequestDto.getPassword(),DatetimeConverter(),DatetimeConverter());
    }

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

    @Override
    public Optional<ScheduleResponseDto> findScheduleById(Long id) {
        List<ScheduleResponseDto> result = jdbcTemplate.query("select * from schedule where id = ?", scheduleRowMapper2(), id);
        return result.stream().findAny();
    }

    @Override
    public ScheduleResponseDto findScheduleByIdOrElseThrow(Long id) {
        List<ScheduleResponseDto> result = jdbcTemplate.query("select * from schedule where id = ?", scheduleRowMapper2(), id);
        return result.stream().findAny().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id));
    }

    public String DatetimeConverter(){
         return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

    }
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