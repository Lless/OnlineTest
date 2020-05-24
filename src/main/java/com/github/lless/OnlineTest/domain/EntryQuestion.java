package com.github.lless.OnlineTest.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class EntryQuestion implements Question{
    private Long id;
    @JsonView(Views.Question.class)
    private final QuestionType type = QuestionType.ENTRY_QUESTION;
    @JsonView(Views.Question.class)
    private final String question;
    @JsonView(Views.AnsweredQuestion.class)
    private final String answer;

    @Override
    public boolean checkAnswer(Object answer) {
        if (!(answer instanceof String)) return false;
        return this.answer.equals(answer);
    }
}
