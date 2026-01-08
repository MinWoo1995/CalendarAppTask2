package com.example.spring_calendarapptask2.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor // JSON 데이터를 객체로 바꿀 때
public class ScheduleRequestDto {

    @NotBlank(message = "할 일 제목은 필수입니다.")
    @Size(max = 10, message = "할 일 제목은 10글자 이내여야 합니다.")
    private String scheduleTitle;

    @NotBlank(message = "할 일 내용은 필수입니다.")
    private String scheduleContent;

    @NotBlank(message = "유저명은 필수입니다.")
    private String userName;
}
