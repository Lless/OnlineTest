package com.github.lless.OnlineTest.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BasicAnswerInfo {
    private Long questionId;
    private String answer;
}
