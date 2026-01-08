package com.example.spring_calendarapptask2.schedule.entity;

import com.example.spring_calendarapptask2.schedule.dto.ScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)//작성일, 수정일 자동화
public class ScheduleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
    private String userName;
    private String scheduleTitle;
    private String scheduleContent;

    public ScheduleEntity(ScheduleRequestDto requestDto) {
        this.userName = requestDto.getUserName();
        this.scheduleTitle = requestDto.getScheduleTitle();
        this.scheduleContent = requestDto.getScheduleContent();
    }

    public void upDateSchedule(String title, String content) {
        this.scheduleTitle = title;
        this.scheduleContent = content;
    }

}
