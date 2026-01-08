package com.example.spring_calendarapptask2.user.dto;

import com.example.spring_calendarapptask2.user.entity.UserEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {
    private Long id;
    private String userName;
    private String userEmail;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;

    public UserResponseDto(UserEntity entity) {
        this.id = entity.getId();
        this.userName = entity.getUserName();
        this.userEmail = entity.getUserEmail();
        this.createdDate = entity.getCreatedDate();
        this.lastModifiedDate = entity.getLastModifiedDate();
    }
}
