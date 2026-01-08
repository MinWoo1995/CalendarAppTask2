package com.example.spring_calendarapptask2.schedule.dto;

import com.example.spring_calendarapptask2.schedule.entity.ScheduleEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private Long id; // 식별자 필수
    private String userName;
    private String scheduleTitle;
    private String scheduleContent;
    private LocalDateTime createdDate; // JPA Auditing 활용 필드
    private LocalDateTime lastModifiedDate; // JPA Auditing 활용 필드

    public ScheduleResponseDto(ScheduleEntity entity) {
        this.id = entity.getId();
        this.userName = entity.getUserName();
        this.scheduleTitle = entity.getScheduleTitle();
        this.scheduleContent = entity.getScheduleContent();
        this.createdDate = entity.getCreatedDate();
        this.lastModifiedDate = entity.getLastModifiedDate();
    }
}
