package com.example.spring_calendarapptask2.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice // 모든 컨트롤러에서 발생하는 예외를 여기서 가로챈다
public class GlobalExceptionHandler {

    // ResponseStatusException이 발생하면 이 메서드가 실행
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatusException(ResponseStatusException ex) {
        Map<String, Object> body = new LinkedHashMap<>();

        //상태 코드
        body.put("status", ex.getStatusCode().value());
        //에러 종류
        body.put("error", ex.getStatusCode().toString());
        //작성한 오류 메세지 출력
        body.put("message", ex.getReason());

        return new ResponseEntity<>(body, ex.getStatusCode());
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, Object> body = new LinkedHashMap<>();

        body.put("status", HttpStatus.BAD_REQUEST.value()); // 400
        body.put("error", "Bad Request");
        body.put("message", ex.getMessage()); // IllegalArgumentException은 getMessage()를 사용

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
