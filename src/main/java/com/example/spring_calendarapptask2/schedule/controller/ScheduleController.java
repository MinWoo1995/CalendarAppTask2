package com.example.spring_calendarapptask2.schedule.controller;

import com.example.spring_calendarapptask2.schedule.dto.ScheduleRequestDto;
import com.example.spring_calendarapptask2.schedule.dto.ScheduleResponseDto;
import com.example.spring_calendarapptask2.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    //일정 생성 API
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        return ResponseEntity.ok(scheduleService.createSchedule(requestDto));
    }

    //일정 단건 조회 API
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> getScheduleOne(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.getScheduleById(id));
    }

    //일정 전체 조회 API
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> getScheduleAll() {
        List<ScheduleResponseDto> responseList = scheduleService.getSchedule();
        return ResponseEntity.ok(responseList);
    }

    //일정 수정 API
    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleRequestDto requestDto){
        return ResponseEntity.ok(scheduleService.updateSchedule(scheduleId, requestDto));
    }

    //일정 삭제 API
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<Long> deleteSchedule(@PathVariable Long scheduleId){
        Long deletedId = scheduleService.deleteSchedule(scheduleId);
        return ResponseEntity.ok(deletedId);
    }
}
