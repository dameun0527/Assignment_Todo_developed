package com.sparta.assignment_todo_developed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AssignmentTodoDevelopedApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssignmentTodoDevelopedApplication.class, args);
    }

}
