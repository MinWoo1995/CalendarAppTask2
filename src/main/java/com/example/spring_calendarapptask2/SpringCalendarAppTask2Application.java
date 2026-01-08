package com.example.spring_calendarapptask2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing//작성일, 수정일 자동화
@SpringBootApplication
public class SpringCalendarAppTask2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringCalendarAppTask2Application.class, args);
    }

}
