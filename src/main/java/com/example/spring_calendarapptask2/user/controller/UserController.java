package com.example.spring_calendarapptask2.user.controller;

import com.example.spring_calendarapptask2.schedule.dto.ScheduleRequestDto;
import com.example.spring_calendarapptask2.schedule.dto.ScheduleResponseDto;
import com.example.spring_calendarapptask2.schedule.service.ScheduleService;
import com.example.spring_calendarapptask2.user.dto.UserLoginRequestDto;
import com.example.spring_calendarapptask2.user.dto.UserLoginResponseDto;
import com.example.spring_calendarapptask2.user.dto.UserRequestDto;
import com.example.spring_calendarapptask2.user.dto.UserResponseDto;
import com.example.spring_calendarapptask2.user.entity.UserEntity;
import com.example.spring_calendarapptask2.user.repository.UserRepository;
import com.example.spring_calendarapptask2.user.service.UserService;
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
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    //로그인 확인
    private void loginSessionCheck(HttpServletRequest request) {
        HttpSession session = request.getSession(false);//세션이 없다고 새로 만들지 않는것으로

        if (session == null || session.getAttribute("loginUser") == null) {
            //인증 실패시
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"로그인이 필요합니다.");
        }
    }

    //유저 회원가입 API
    @PostMapping
    public ResponseEntity<UserResponseDto> userSignup(@Valid @RequestBody UserRequestDto requestDto) {
        return ResponseEntity.ok(userService.signup(requestDto));
    }

    //유저 단건 조회 API
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUsereOne(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserOne(id));
    }

    //유저 로그인 API
    @PostMapping("/login")
    public UserLoginResponseDto login(
            @Valid @RequestBody UserLoginRequestDto loginRequestDto,
            HttpServletRequest request //서블릿 리퀘스트를 받아옵니다.
    ) {
        //서비스에서 이메일/비밀번호 검증 (성공하면 유저 엔티티 반환)
        UserLoginResponseDto responseDto = userService.login(loginRequestDto.getUserEmail(), loginRequestDto.getUserPassword());

        //세션 생성
        //request.getSession()은 세션이 있으면 반환, 없으면 새로 생성
        HttpSession session = request.getSession();

        //세션에 로그인한 유저의 정보를 저장합니다.
        // "loginUser"라는 이름의 바구니에 유저의 ID(Long)를 담기
        session.setAttribute("loginUser", responseDto.getId());

        return responseDto;
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
            @Valid @RequestBody UserRequestDto requestDto,
            HttpServletRequest request){
        loginSessionCheck(request);//로그인 확인
        return ResponseEntity.ok(userService.updateUser(userId, requestDto));
    }

    //유저 삭제 API
    @DeleteMapping("/{userId}")
    public ResponseEntity<Long> deleteUser(@PathVariable Long userId,HttpServletRequest request){
        loginSessionCheck(request);//로그인 확인
        Long deletedId = userService.deleteUser(userId);
        return ResponseEntity.ok(deletedId);
    }
}
