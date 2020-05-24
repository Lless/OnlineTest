package com.github.lless.OnlineTest.data;

public interface StatisticsRepository {
    Long getStartedTestUserCount();
    Long getFinishedTestUserCount();
    Long getAllCorrectAnsweredUserCount();
}
