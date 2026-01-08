package com.example.spring_calendarapptask2.user.entity;

import com.example.spring_calendarapptask2.common.entity.BaseEntity;
import com.example.spring_calendarapptask2.user.dto.UserRequestDto;
import com.example.spring_calendarapptask2.user.dto.UserResponseDto;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import jakarta.persistence.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class UserEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String userEmail;


    public UserEntity(UserRequestDto requestDto) {
        this.userName = requestDto.getUserName();
        this.userEmail = requestDto.getUserEmail();
    }

    public void updateUser(UserRequestDto requestDto) {
        this.userName = requestDto.getUserName();
        this.userEmail = requestDto.getUserEmail();
    }
}

