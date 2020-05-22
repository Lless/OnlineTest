package com.github.lless.OnlineTest.domain;

public interface Question {
    boolean checkAnswer(String answer);
    Long getId();
}
