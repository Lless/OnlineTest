package com.github.lless.OnlineTest.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExtendedAnswerInfo {
    @JsonView(Views.Question.class)
    private Question question;
    @JsonView(Views.Question.class)
    private String myAnswer;
}
