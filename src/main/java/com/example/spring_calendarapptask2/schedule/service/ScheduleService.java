package com.example.spring_calendarapptask2.schedule.service;

import com.example.spring_calendarapptask2.schedule.dto.ScheduleRequestDto;
import com.example.spring_calendarapptask2.schedule.dto.ScheduleResponseDto;
import com.example.spring_calendarapptask2.schedule.entity.ScheduleEntity;
import com.example.spring_calendarapptask2.schedule.reposistory.ScheduleRepository;
import com.example.spring_calendarapptask2.user.entity.UserEntity;
import com.example.spring_calendarapptask2.user.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    //일정 생성
    @Transactional
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto,Long loginUserId) {
        //DTO에 담긴 userId로 실제 유저를 찾기
        UserEntity user = userRepository.findById(loginUserId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저를 찾을 수 없습니다."));

        //찾은 유저 객체를 일정 엔티티에 토스
        ScheduleEntity scheduleEntity = new ScheduleEntity(requestDto, user);

        ScheduleEntity savedSchedule = scheduleRepository.save(scheduleEntity);
        return new ScheduleResponseDto(savedSchedule);
    }

    //일정 수정
    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto) {
        //수정할려는 일정이 있는지 확인하기
        ScheduleEntity scheduleEntity = scheduleRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("해당 일정이 존재하지 않습니다."));
        //일정이 있다면 가져와서 데이터를 수정
        scheduleEntity.upDateSchedule(requestDto.getScheduleTitle(), requestDto.getScheduleContent());

        //DB에 반영
        return new ScheduleResponseDto(scheduleEntity);
    }

    //일정 삭제
    @Transactional
    public Long deleteSchedule(Long id) {
        //삭제할려는 일정이 있는지 확인하기
        ScheduleEntity scheduleEntity = scheduleRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("해당 일정이 존재하지 않습니다."));
        //찾은 객체 삭제 하기
        scheduleRepository.delete(scheduleEntity);

        //삭제 성공 리턴하기
        return id;
    }

    //전체 조회
    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> getSchedule() {
        //저장소에 있는 모든 일정 가져오기
        List<ScheduleEntity> allSchedule = scheduleRepository.findAll();

        //게시글이 있는지?
        if(allSchedule.isEmpty()){
            throw new IllegalArgumentException("등록된 일정이 없습니다.");
        }

        //List<ScheduleEntity>->List<ScheduleResponseDto>
        List<ScheduleResponseDto> dtos = new ArrayList<>();

        for(ScheduleEntity scheduleEntity : scheduleRepository.findAll()){
            dtos.add(new ScheduleResponseDto(scheduleEntity));
        }

        //결과 반환
        return dtos;
    }

    //단건 조회
    @Transactional(readOnly = true)
    public ScheduleResponseDto getScheduleById(Long id) {
        ScheduleEntity scheduleEntity = scheduleRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("해당 일정이 존재하지 않습니다."));
        return new ScheduleResponseDto(scheduleEntity);
    }
}
