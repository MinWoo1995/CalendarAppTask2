package com.example.spring_calendarapptask2.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequestDto {
    @NotBlank(message = "유저명은 필수입니다.")
    @Size(min = 1, max = 4)
    private String userName;
    @NotBlank(message = "이메일은 필수 입니다.")
    @Size(min = 7, max = 30)
    private String userEmail;
}