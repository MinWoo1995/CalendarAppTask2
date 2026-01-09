package com.example.spring_calendarapptask2.comment.dto;

import com.example.spring_calendarapptask2.comment.entity.CommentEntity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private Long id;
    private String commentContent;
    private String userName;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public CommentResponseDto(CommentEntity comment) {
        this.id = comment.getId();
        this.commentContent = comment.getCommentContent();
        // 작성자 엔티티를 통해 이름을 가져옴
        this.userName = comment.getUserEntity().getUserName();

        // BaseEntity로부터 상속받은 필드들
        this.createdAt = comment.getCreatedDate();
        this.modifiedAt = comment.getLastModifiedDate();
    }
}