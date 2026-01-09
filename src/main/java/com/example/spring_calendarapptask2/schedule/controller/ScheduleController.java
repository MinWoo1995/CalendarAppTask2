package com.example.spring_calendarapptask2.schedule.controller;

import com.example.spring_calendarapptask2.schedule.dto.ScheduleRequestDto;
import com.example.spring_calendarapptask2.schedule.dto.ScheduleResponseDto;
import com.example.spring_calendarapptask2.schedule.service.ScheduleService;
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
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    //로그인 확인
    private void loginSessionCheck(HttpServletRequest request) {
        HttpSession session = request.getSession(false);//세션이 없다고 새로 만들지 않는것으로

        if (session == null || session.getAttribute("loginUser") == null) {
            //인증 실패시
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"로그인이 필요합니다.");
        }
    }

    //일정 생성 API
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@Valid @RequestBody ScheduleRequestDto requestDto, HttpServletRequest request) {
        loginSessionCheck(request);

        HttpSession session = request.getSession(false);
        Long loginUserId = (Long) session.getAttribute("loginUser");

        return ResponseEntity.ok(scheduleService.createSchedule(requestDto,loginUserId));
    }

    //일정 단건 조회 API
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> getScheduleOne(@PathVariable Long id,HttpServletRequest request) {
        loginSessionCheck(request);
        return ResponseEntity.ok(scheduleService.getScheduleById(id));
    }

    //일정 전체 조회 API
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getScheduleAll(HttpServletRequest request) {
        loginSessionCheck(request);
        List<ScheduleResponseDto> responseList = scheduleService.getSchedule();
        return ResponseEntity.ok(responseList);
    }

    //일정 수정 API
    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long scheduleId,
            @Valid @RequestBody ScheduleRequestDto requestDto,
            HttpServletRequest request){

        loginSessionCheck(request);

        return ResponseEntity.ok(scheduleService.updateSchedule(scheduleId, requestDto));
    }

    //일정 삭제 API
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Long> deleteSchedule(@PathVariable Long scheduleId,HttpServletRequest request){
        loginSessionCheck(request);
        Long deletedId = scheduleService.deleteSchedule(scheduleId);
        return ResponseEntity.ok(deletedId);
    }
}
