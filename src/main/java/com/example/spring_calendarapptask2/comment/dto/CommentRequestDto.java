package com.example.spring_calendarapptask2.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentRequestDto {

    @NotNull(message = "일정 ID는 필수입니다.")
    private Long scheduleId;

    @NotBlank(message = "댓글 내용은 비어있을 수 없습니다.")
    @Size(max = 100,message = "댓글은 100자 이내로 작성 부탁드립니다.")
    private String commentContent;
}
