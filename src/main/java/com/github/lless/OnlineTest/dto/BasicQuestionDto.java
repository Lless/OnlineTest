package com.github.lless.OnlineTest.dto;

import com.github.lless.OnlineTest.domain.QuestionType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BasicQuestionDto {
    private Long id;
    private QuestionType type;
}
