package com.example.spring_calendarapptask2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootTest
@EnableJpaAuditing//작성일, 수정일 자동화
class SpringCalendarAppTask2ApplicationTests {

    @Test
    void contextLoads() {
    }

}
