package com.github.lless.OnlineTest.controller;

import com.github.lless.OnlineTest.data.EntryQuestionRepository;
import com.github.lless.OnlineTest.domain.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final EntryQuestionRepository repo;

    @GetMapping
    private Question getQuestion() {
        return repo.findById(1L);
    }
}
