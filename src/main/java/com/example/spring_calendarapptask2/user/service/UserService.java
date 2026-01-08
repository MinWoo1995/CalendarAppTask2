package com.example.spring_calendarapptask2.user.service;

import com.example.spring_calendarapptask2.schedule.reposistory.ScheduleRepository;
import com.example.spring_calendarapptask2.user.dto.UserRequestDto;
import com.example.spring_calendarapptask2.user.dto.UserResponseDto;
import com.example.spring_calendarapptask2.user.entity.UserEntity;
import com.example.spring_calendarapptask2.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    //유저 생성
    @Transactional
    public UserResponseDto createUser(UserRequestDto requestDto) {
        UserEntity userEntity = new UserEntity(requestDto);

        UserEntity savedUser = userRepository.save(userEntity);

        return new UserResponseDto(savedUser);
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
