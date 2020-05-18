package com.github.lless.OnlineTest.domain;

import lombok.Data;

@Data
public class EntryQuestion implements Question {
    private final String question;
    private final String answer;
}
