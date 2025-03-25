package com.example.schedule.repository;

import com.example.schedule.service.ScheduleService;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class JdbcTemplateScheduleRepository implements ScheduleRepository {

    private final JdbcTemplate jdbcTemplate;

     public JdbcTemplateScheduleRepository(DataSource dataSource){
         this.jdbcTemplate = new JdbcTemplate(dataSource);
     }

}
