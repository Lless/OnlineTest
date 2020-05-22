package com.github.lless.OnlineTest.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.lless.OnlineTest.domain.Question;
import com.github.lless.OnlineTest.domain.User;
import com.github.lless.OnlineTest.domain.Views;
import com.github.lless.OnlineTest.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final QuestionService service;

    @GetMapping
    @JsonView(Views.Question.class)
    public Question getQuestion(@AuthenticationPrincipal User user) {
        return service.getQuestion(user);
    }


    @PostMapping
    public void answerQuestion(@RequestBody String answer, @AuthenticationPrincipal User user) {
        service.answerQuestion(user, answer);
    }
}
