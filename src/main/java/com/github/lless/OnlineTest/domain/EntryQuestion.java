package com.github.lless.OnlineTest.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntryQuestion implements Question {
    private Long id;
    private String question;
    private String answer;

    public EntryQuestion(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
}
