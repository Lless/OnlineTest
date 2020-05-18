package com.github.lless.OnlineTest.controller;

import com.github.lless.OnlineTest.domain.EntryQuestion;
import com.github.lless.OnlineTest.domain.Question;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping
    private Question getQuestion() {
        return new EntryQuestion("?", "!");
    }
}
