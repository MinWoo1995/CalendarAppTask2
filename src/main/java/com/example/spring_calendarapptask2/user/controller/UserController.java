package com.example.spring_calendarapptask2.user.controller;

import com.example.spring_calendarapptask2.schedule.dto.ScheduleRequestDto;
import com.example.spring_calendarapptask2.schedule.dto.ScheduleResponseDto;
import com.example.spring_calendarapptask2.schedule.service.ScheduleService;
import com.example.spring_calendarapptask2.user.dto.UserRequestDto;
import com.example.spring_calendarapptask2.user.dto.UserResponseDto;
import com.example.spring_calendarapptask2.user.repository.UserRepository;
import com.example.spring_calendarapptask2.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //유저 생성 API
    @PostMapping
    public ResponseEntity<UserResponseDto> createSchedule(@RequestBody UserRequestDto requestDto) {
        return ResponseEntity.ok(userService.createUser(requestDto));
    }
    //유저 단건 조회 API
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUsereOne(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserOne(id));
    }

    //유저 전체 조회 API
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getUserAll() {
        List<UserResponseDto> responseList = userService.getUser();
        return ResponseEntity.ok(responseList);
    }

    //유저 수정 API
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable Long userId,
            @RequestBody UserRequestDto requestDto){
        return ResponseEntity.ok(userService.updateUser(userId, requestDto));
    }

    //유저 삭제 API
    @DeleteMapping("/{userId}")
    public ResponseEntity<Long> deleteUser(@PathVariable Long userId){
        Long deletedId = userService.deleteUser(userId);
        return ResponseEntity.ok(deletedId);
    }
}
