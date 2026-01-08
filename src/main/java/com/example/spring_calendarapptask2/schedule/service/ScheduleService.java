package com.example.spring_calendarapptask2.schedule.service;

import com.example.spring_calendarapptask2.schedule.dto.ScheduleRequestDto;
import com.example.spring_calendarapptask2.schedule.dto.ScheduleResponseDto;
import com.example.spring_calendarapptask2.schedule.entity.ScheduleEntity;
import com.example.spring_calendarapptask2.schedule.reposistory.ScheduleRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    //일정 생성
    @Transactional
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        //RequestDto -> Entity 변환
        ScheduleEntity scheduleEntity = new ScheduleEntity(requestDto);

        //DB 저장
        ScheduleEntity savedSchedule = scheduleRepository.save(scheduleEntity);

        //Entity -> ResponseDto 변환 후 반환
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
            throw new IllegalArgumentException("일치하는 작성자의 게시글이 없습니다.");
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
