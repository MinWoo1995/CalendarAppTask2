package com.example.spring_calendarapptask2.user.service;

import com.example.spring_calendarapptask2.schedule.reposistory.ScheduleRepository;
import com.example.spring_calendarapptask2.user.dto.UserLoginResponseDto;
import com.example.spring_calendarapptask2.user.dto.UserRequestDto;
import com.example.spring_calendarapptask2.user.dto.UserResponseDto;
import com.example.spring_calendarapptask2.user.entity.UserEntity;
import com.example.spring_calendarapptask2.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    //유저 회원가입
    @Transactional
    public UserResponseDto signup(UserRequestDto requestDto) {
        userRepository.findByUserEmail(requestDto.getUserEmail()).ifPresent(u -> {
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        });
        UserEntity userEntity = new UserEntity(requestDto);
        UserEntity savedUser = userRepository.save(userEntity);

        return new UserResponseDto(savedUser);
    }

    //유저 로그인
    @Transactional(readOnly = true)
    public UserLoginResponseDto login(String email, String password) {
        //이메일로 유저 찾기
        UserEntity user = userRepository.findByUserEmail(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED,"일치하는 이메일 정보가 없습니다."));

        //비밀번호 대조
        if (!user.getUserPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return new UserLoginResponseDto(user,"로그인에 성공하였습니다.");
    }

    //유저 전체 조회
    @Transactional(readOnly = true)
    public List<UserResponseDto> getUser() {
        List<UserEntity> allUser = userRepository.findAll();

        if (allUser.isEmpty()) {
            throw new IllegalArgumentException("등록된 유저가 없습니다.");
        }

        List<UserResponseDto> dtos = new ArrayList<>();
        for (UserEntity userEntity : allUser) {
            dtos.add(new UserResponseDto(userEntity));
        }
        return dtos;
    }

    //유저 단건 조회
    @Transactional(readOnly = true)
    public UserResponseDto getUserOne(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        return new UserResponseDto(userEntity);
    }

    //유저 수정
    @Transactional
    public UserResponseDto updateUser(Long id,UserRequestDto requestDto) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("존재 하지 않는 유저입니다."));

        userEntity.updateUser(requestDto);

        return new UserResponseDto(userRepository.save(userEntity));
    }

    //유저 삭제
    @Transactional
    public Long deleteUser(Long id) {
        UserEntity userEntity = userRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("존재 하지 않는 유저입니다."));

        userRepository.delete(userEntity);

        return id;
    }
}
