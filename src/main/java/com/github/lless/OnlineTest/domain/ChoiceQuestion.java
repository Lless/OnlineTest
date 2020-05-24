package com.github.lless.OnlineTest.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ChoiceQuestion implements Question {
    private Long id;
    @JsonView(Views.Question.class)
    private final QuestionType type = QuestionType.CHOICE_QUESTION;
    @JsonView(Views.Question.class)
    private final String question;
    @JsonView(Views.Question.class)
    private final List<String> options;
    @JsonView(Views.AnsweredQuestion.class)
    private final Integer answer;


    @Override
    public boolean checkAnswer(Object answer) {
        if (!(answer instanceof Integer)) return false;
        return this.answer.equals(answer);
    }
}
