package com.example.spring_calendarapptask2.comment.service;

import com.example.spring_calendarapptask2.comment.dto.CommentRequestDto;
import com.example.spring_calendarapptask2.comment.dto.CommentResponseDto;
import com.example.spring_calendarapptask2.comment.entity.CommentEntity;
import com.example.spring_calendarapptask2.comment.repository.CommentRepository;
import com.example.spring_calendarapptask2.schedule.dto.ScheduleRequestDto;
import com.example.spring_calendarapptask2.schedule.dto.ScheduleResponseDto;
import com.example.spring_calendarapptask2.schedule.entity.ScheduleEntity;
import com.example.spring_calendarapptask2.schedule.reposistory.ScheduleRepository;
import com.example.spring_calendarapptask2.user.entity.UserEntity;
import com.example.spring_calendarapptask2.user.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    //댓글 생성
    @Transactional
    public CommentResponseDto createComment(CommentRequestDto requestDto, Long loginUserId,Long scheduleId) {
        //유저 조회
        UserEntity user = userRepository.findById(loginUserId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."));

        //댓글 조회
        ScheduleEntity schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "일정을 찾을 수 없습니다."));

        //댓글 생성 및 저장
        CommentEntity commentEntity = new CommentEntity(requestDto, schedule, user);
        CommentEntity savedComment = commentRepository.save(commentEntity);

        return new CommentResponseDto(savedComment);
    }

    //댓글 수정
    @Transactional
    public CommentResponseDto updateComment(Long id, CommentRequestDto requestDto) {
        //수정할려는 댓글이 있는지 확인하기
        CommentEntity commentEntity = commentRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
        //댓글이 있다면 가져와서 데이터를 수정
        commentEntity.updateComment(requestDto);

        //DB에 반영
        return new CommentResponseDto(commentEntity);
    }

    //댓글 삭제
    @Transactional
    public Long deleteComment(Long id) {
        //삭제할려는 댓글이 있는지 확인하기
        CommentEntity commentEntity = commentRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
        //찾은 객체 삭제 하기
        commentRepository.delete(commentEntity);

        //삭제 성공 리턴하기
        return id;
    }

    //전체 조회
    @Transactional(readOnly = true)
    public List<CommentResponseDto> getComment() {
        //저장소에 있는 모든 댓글 가져오기
        List<CommentEntity> allComment = commentRepository.findAll();

        //댓글이 있는지?
        if(allComment.isEmpty()){
            throw new IllegalArgumentException("등록된 댓글이 없습니다.");
        }

        List<CommentResponseDto> dtos = new ArrayList<>();

        for(CommentEntity commentEntity : allComment){
            dtos.add(new CommentResponseDto(commentEntity));
        }

        //결과 반환
        return dtos;
    }

    //단건 조회
    @Transactional(readOnly = true)
    public CommentResponseDto getCommentById(Long id) {
        CommentEntity commentEntity = commentRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("해당 댓글이 존재하지 않습니다."));
        return new CommentResponseDto(commentEntity);
    }
}
