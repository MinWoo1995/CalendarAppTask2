package com.example.spring_calendarapptask2.user.dto;

import com.example.spring_calendarapptask2.user.entity.UserEntity;
import lombok.Getter;

@Getter
public class UserLoginResponseDto {
    private Long id;
    private String userName;
    private String massage;

    public UserLoginResponseDto(UserEntity user, String massage) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.massage = massage;
    }
}
