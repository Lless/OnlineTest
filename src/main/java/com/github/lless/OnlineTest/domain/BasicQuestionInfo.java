package com.github.lless.OnlineTest.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BasicQuestionInfo {
    private Long id;
    private QuestionType type;
}
