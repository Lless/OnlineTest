package com.github.lless.OnlineTest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BasicAnswerDto {
    private Long questionId;
    private String answer;
}
