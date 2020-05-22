package com.github.lless.OnlineTest.domain;

public interface Question {
    boolean checkAnswer(Object answer);
    Long getId();
}
