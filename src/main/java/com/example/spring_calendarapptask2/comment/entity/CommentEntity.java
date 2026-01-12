package com.example.spring_calendarapptask2.comment.entity;

import com.example.spring_calendarapptask2.comment.dto.CommentRequestDto;
import com.example.spring_calendarapptask2.common.entity.BaseEntity;
import com.example.spring_calendarapptask2.schedule.entity.ScheduleEntity;
import com.example.spring_calendarapptask2.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CommentEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,length = 100)
    private String commentContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Id")
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_Id")
    private ScheduleEntity scheduleEntity;

    public CommentEntity(CommentRequestDto requestDto, ScheduleEntity scheduleEntity, UserEntity userEntity) {
        this.commentContent = requestDto.getCommentContent();
        this.userEntity = userEntity;
        this.scheduleEntity = scheduleEntity;
    }

    public void updateComment(CommentRequestDto requestDto){
        this.commentContent = requestDto.getCommentContent();
        this.setLastModifiedDate(LocalDateTime.now());//수정 시간 업데이트
    }

}
