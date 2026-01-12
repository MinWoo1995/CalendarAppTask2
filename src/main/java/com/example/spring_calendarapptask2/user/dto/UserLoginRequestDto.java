package com.example.spring_calendarapptask2.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

//로그인 요청
@Getter
@NoArgsConstructor
public class UserLoginRequestDto {
    @NotBlank(message = "이메일은 필수 입니다.")
    @Size(min = 7, max = 30,message = "이메일의 크기가 7~30 사이여야 합니다.")
    private String userEmail;
    @NotBlank(message="비밀번호는 필수 입니다.")
    @Size(min=8,message="비밀번호는 최소 8글자 이상이어야 합니다.")
    private String userPassword;
}
