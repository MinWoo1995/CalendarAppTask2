package com.example.spring_calendarapptask2.schedule.reposistory;

import com.example.spring_calendarapptask2.schedule.entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<ScheduleEntity,Long>
{
}
