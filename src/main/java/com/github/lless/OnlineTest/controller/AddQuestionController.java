package com.github.lless.OnlineTest.controller;

import com.github.lless.OnlineTest.data.QuestionRepository;
import com.github.lless.OnlineTest.domain.ChoiceQuestion;
import com.github.lless.OnlineTest.domain.EntryQuestion;
import com.github.lless.OnlineTest.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AddQuestionController {
    private final QuestionRepository repo;

    @PostMapping("/addEntryQuestion")
    public void addAnswer(@RequestBody EntryQuestion question, @AuthenticationPrincipal User user){
        repo.addQuestion(question, user);
    }

    @PostMapping("/addChoiceQuestion")
    public void addAnswer(@RequestBody ChoiceQuestion question, @AuthenticationPrincipal User user){
        repo.addQuestion(question, user);
    }
}
