package com.github.lless.OnlineTest.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.lless.OnlineTest.data.EntryQuestionRepository;
import com.github.lless.OnlineTest.domain.Question;
import com.github.lless.OnlineTest.domain.User;
import com.github.lless.OnlineTest.domain.Views;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class TestController {
    private final EntryQuestionRepository repo;

    @GetMapping
    @JsonView(Views.Question.class)
    public Question getQuestion() {
        return repo.findById(1L);
    }


    @PostMapping
    public void answerQuestion(@RequestBody String answer, @AuthenticationPrincipal User user) {
        System.out.println(answer);
    }
}
