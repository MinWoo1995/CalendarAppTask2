package com.example.spring_calendarapptask2.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

//회원가입 요청
@Getter
@NoArgsConstructor
public class UserRequestDto {
    @NotBlank(message = "유저명은 필수입니다.")
    @Size(min = 1, max = 4,message = "유저의 이름은 1~4자 사이여야 합니다.")
    private String userName;
    @NotBlank(message = "이메일은 필수 입니다.")
    @Size(min = 7, max = 30,message = "이메일의 크기가 7~30 사이여야 합니다.")
    private String userEmail;
    @NotBlank(message="비밀번호는 필수 입니다.")
    @Size(min=8,message="비밀번호는 최소 8글자 이상이어야 합니다.")
    private String userPassword;
}