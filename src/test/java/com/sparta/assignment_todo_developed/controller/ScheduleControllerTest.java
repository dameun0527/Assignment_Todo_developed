//package com.sparta.assignment_todo_developed.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sparta.assignment_todo_developed.dto.schedule.ScheduleDto;
//import com.sparta.assignment_todo_developed.entity.Schedule;
//import com.sparta.assignment_todo_developed.repository.ScheduleRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class ScheduleControllerTest {
//
//    @Autowired
//    protected MockMvc mockMvc;
//
//    @Autowired
//    // 직렬화, 역직렬화를 위한 클래스
//    protected ObjectMapper objectMapper;
//
//    @Autowired
//    ScheduleRepository scheduleRepository;
//
//    @BeforeEach
//    public void mockMvcSetup() {
//        scheduleRepository.deleteAll();
//    }
//
//    // [API Test]
//    @DisplayName("createSchedule: 일정 생성에 성공한다.")
//    @Test
//    public void createSchedule() throws Exception {
//        // given: 테스트할 데이터
//        final String url = "/api/schedule"; // API URL
//        final String title = "title"; // 일정 제목
//        final String username = "username"; // 사용자 이름
//        final String password = "password"; // 비밀번호
//        final String content = "content"; // 내용
//
//        // ScheduleDto.CreateRequestDto 객체 생성 및 JSON으로 직렬화
//        final ScheduleDto.CreateRequestDto userRequest = new ScheduleDto.CreateRequestDto(title, username, password, content);
//        final String requestBody = objectMapper.writeValueAsString(userRequest);
//
//        // when
//        // API 호출
//        ResultActions result = mockMvc.perform(post(url)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(requestBody));
//
//        // then
//        // 검증
//        result.andExpect(status().isCreated());
//        List<Schedule> schedules = scheduleRepository.findAll();
//
//        assertThat(schedules.size()).isEqualTo(1);
//        assertThat(schedules.get(0).getTitle()).isEqualTo(title);
//        assertThat(schedules.get(0).getCreator()).isEqualTo(username);
//        assertThat(schedules.get(0).getContent()).isEqualTo(content);
//    }
//
//    @DisplayName("findAllSchedules: 전체 일정 조회에 성공한다.")
//    @Test
//    public void findAllSchedules() throws Exception {
//        // given
//        final String url = "/api/schedule/list";
//        final String title = "title";
//        final String username = "username";
//        final String password = "password";
//        final String content = "content";
//
//        scheduleRepository.save(Schedule.builder()
//                .title(title)
//                .username(username)
//                .password(password)
//                .content(content)
//                .build());
//
//        // when
//        final ResultActions resultActions = mockMvc.perform(get(url)
//                .accept(MediaType.APPLICATION_JSON));
//
//        // then
//        resultActions
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].title").value(title))
//                .andExpect(jsonPath("$[0].username").value(username))
//                .andExpect(jsonPath("$[0].content").value(content));
//    }
//
//    @DisplayName("findSchedule: 선택 일정 조회에 성공한다.")
//    @Test
//    public void findSchedule() throws Exception {
//        // given
//        final String url = "/api/schedule/{id}";
//        final String title = "title";
//        final String username = "username";
//        final String content = "content";
//
//        Schedule savedSchedule = scheduleRepository.save(Schedule.builder()
//                .title(title)
//                .username(username)
//                .content(content)
//                .build());
//
//        // when
//        final ResultActions resultActions = mockMvc.perform(get(url, savedSchedule.getId()));
//
//        // then
//        resultActions
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.title").value(title))
//                .andExpect(jsonPath("$.username").value(username))
//                .andExpect(jsonPath("$.content").value(content));
//    }
//
//    @DisplayName("deleteSchedule: 일정 삭제에 성공한다.")
//    @Test
//    public void deleteSchedule() throws Exception {
//        // given
//        final String url = "/api/schedule/{id}";
//        final String title = "title";
//        final String username = "username";
//        final String content = "content";
//
//        Schedule savedSchedule = scheduleRepository.save(Schedule.builder()
//                .title(title)
//                .username(username)
//                .content(content)
//                .build());
//
//        // when
//        mockMvc.perform(delete(url, savedSchedule.getId()))
//                .andExpect(status().isOk());
//
//        // then
//        List<Schedule> schedules = scheduleRepository.findAll();
//        assertThat(schedules).isEmpty();
//    }
//}
