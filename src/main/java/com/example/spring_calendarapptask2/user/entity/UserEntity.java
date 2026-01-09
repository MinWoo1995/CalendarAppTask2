package com.example.spring_calendarapptask2.user.entity;

import com.example.spring_calendarapptask2.common.entity.BaseEntity;
import com.example.spring_calendarapptask2.user.dto.UserRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
    @Column(updatable = false)
    private String userPassword;


    public UserEntity(UserRequestDto requestDto, String encodedPassword) {
        this.userName = requestDto.getUserName();
        this.userEmail = requestDto.getUserEmail();
        this.userPassword = encodedPassword;
    }

    public void updateUser(UserRequestDto requestDto) {
        this.userName = requestDto.getUserName();
        this.userEmail = requestDto.getUserEmail();
        this.setLastModifiedDate(LocalDateTime.now());
    }
}

