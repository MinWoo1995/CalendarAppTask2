package com.example.spring_calendarapptask2.schedule.entity;

import com.example.spring_calendarapptask2.comment.entity.CommentEntity;
import com.example.spring_calendarapptask2.common.entity.BaseEntity;
import com.example.spring_calendarapptask2.schedule.dto.ScheduleRequestDto;
import com.example.spring_calendarapptask2.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)//작성일, 수정일 자동화
public class ScheduleEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String scheduleTitle;
    private String scheduleContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // DB에는 작성자 이름 대신 유저 ID(FK)가 저장
    private UserEntity user;

    @OneToMany(mappedBy = "scheduleEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentEntity> comments = new ArrayList<>();

    public ScheduleEntity(ScheduleRequestDto requestDto, UserEntity user) {
        this.scheduleTitle = requestDto.getScheduleTitle();
        this.scheduleContent = requestDto.getScheduleContent();
        this.user = user;
    }

    public void upDateSchedule(String title, String content) {
        this.scheduleTitle = title;
        this.scheduleContent = content;
        this.setLastModifiedDate(LocalDateTime.now());
    }

}
