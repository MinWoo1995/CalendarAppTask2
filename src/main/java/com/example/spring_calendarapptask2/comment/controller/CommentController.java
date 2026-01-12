package com.example.spring_calendarapptask2.comment.controller;

import com.example.spring_calendarapptask2.comment.dto.CommentRequestDto;
import com.example.spring_calendarapptask2.comment.dto.CommentResponseDto;
import com.example.spring_calendarapptask2.comment.service.CommentService;
import com.example.spring_calendarapptask2.schedule.dto.ScheduleRequestDto;
import com.example.spring_calendarapptask2.schedule.dto.ScheduleResponseDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    //로그인 확인
    private void loginSessionCheck(HttpServletRequest request) {
        HttpSession session = request.getSession(false);//세션이 없다고 새로 만들지 않는것으로

        if (session == null || session.getAttribute("loginUser") == null) {
            //인증 실패시
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"로그인이 필요합니다.");
        }
    }

    //댓글 생성 API
    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@Valid @RequestBody CommentRequestDto requestDto, HttpServletRequest request) {
        loginSessionCheck(request);//로그인 하였는지?

        HttpSession session = request.getSession(false);//세션정보를 가져온다
        Long loginUserId = (Long) session.getAttribute("loginUser");//고유 식별자를 꺼내온다

        //생성 서비스 호출
        return ResponseEntity.ok(commentService.createComment(requestDto,loginUserId,requestDto.getScheduleId()));
    }

    //댓글 단건 조회 API
    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDto> getCommentOne(@PathVariable Long id,HttpServletRequest request) {
        loginSessionCheck(request);//로그인 하였는지?
        //단건 조회 서비스 호출
        return ResponseEntity.ok(commentService.getCommentById(id));
    }

    //댓글 전체 조회 API
    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> getCommentAll(HttpServletRequest request) {
        loginSessionCheck(request);//로그인 하였는지?
        List<CommentResponseDto> responseList = commentService.getComment();//전체 조회 서비스 호출 및 배열에 저장
        return ResponseEntity.ok(responseList);
    }

    //댓글 수정 API
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentResponseDto> updateComment(
            @PathVariable Long commentId,
            @Valid @RequestBody CommentRequestDto requestDto,
            HttpServletRequest request){

        loginSessionCheck(request);//로그인 하였는지?

        //수정 서비스 호출
        return ResponseEntity.ok(commentService.updateComment(commentId, requestDto));
    }

    //댓글 삭제 API
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Long> deleteComment(@PathVariable Long commentId,HttpServletRequest request){
        loginSessionCheck(request);//로그인 하였는지?
        Long deletedId = commentService.deleteComment(commentId);//삭제 서비스 호출
        return ResponseEntity.ok(deletedId);
    }
}
