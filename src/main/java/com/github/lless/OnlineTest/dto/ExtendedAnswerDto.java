package com.github.lless.OnlineTest.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.lless.OnlineTest.domain.Question;
import com.github.lless.OnlineTest.domain.Views;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExtendedAnswerDto {
    @JsonView(Views.Question.class)
    private Question question;
    @JsonView(Views.Question.class)
    private String myAnswer;
}
