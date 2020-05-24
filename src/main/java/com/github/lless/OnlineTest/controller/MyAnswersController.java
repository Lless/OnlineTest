package com.github.lless.OnlineTest.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.lless.OnlineTest.domain.ExtendedAnswerInfo;
import com.github.lless.OnlineTest.domain.User;
import com.github.lless.OnlineTest.domain.Views;
import com.github.lless.OnlineTest.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MyAnswersController {
    private final QuestionService service;

    @GetMapping("/answers")
    @JsonView(Views.AnsweredQuestion.class)
    public List<ExtendedAnswerInfo> MyAnswers(@AuthenticationPrincipal User user) {
        return service.getUserAnswers(user);
    }
}
