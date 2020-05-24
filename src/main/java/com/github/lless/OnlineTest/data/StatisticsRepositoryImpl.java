package com.github.lless.OnlineTest.data;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StatisticsRepositoryImpl implements StatisticsRepository {
    private final JdbcTemplate jdbc;

    @Value("${test.size}")
    private Integer testSize;

    @Override
    public Long getStartedTestUserCount() {
        return jdbc.queryForObject("SELECT count(distinct user_id) FROM user_answer", Long.class);
    }

    @Override
    public Long getFinishedTestUserCount() {
        return jdbc.queryForObject("SELECT count(*) from (" +
                        "SELECT distinct user_id FROM user_answer group by user_id having count(*)>=?" +
                        ") as t", Long.class, testSize);
    }

    @Override
    public Long getAllCorrectAnsweredUserCount() {
        return jdbc.queryForObject("SELECT count(*) from (" +
                "SELECT distinct user_id FROM user_answer where correct=true group by user_id having count(*)>=?" +
                ") as t", Long.class, testSize);
    }
}
