package com.github.lless.OnlineTest.data;

import com.github.lless.OnlineTest.domain.User;

public interface StatisticsRepository {
    Long getStartedTestUserCount();
    Long getFinishedTestUserCount();
    Long getAllCorrectAnsweredUserCount();
    Integer getCorrectAnswersCount(User user);
    Integer getAnsweredQuestionsCount(User user);
    Long getUsersWithMoreCorrectAnswersCount(Integer count);
    Long getUsersWithLessCorrectAnswersCount(Integer count);
}
