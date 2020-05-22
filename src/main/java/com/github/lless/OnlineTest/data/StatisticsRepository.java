package com.github.lless.OnlineTest.data;

import com.github.lless.OnlineTest.domain.Question;
import com.github.lless.OnlineTest.domain.User;

import java.util.List;

public interface StatisticsRepository {
    void addAnswer(User user, Question question, Object answer);
    List<Long> getAnsweredQuestionIds(User user);
}
