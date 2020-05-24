package com.github.lless.OnlineTest.data;

import com.github.lless.OnlineTest.dto.BasicAnswerDto;
import com.github.lless.OnlineTest.domain.Question;
import com.github.lless.OnlineTest.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class StatisticsRepositoryImpl implements StatisticsRepository {
    private final JdbcTemplate jdbc;

    @Override
    public void addAnswer(User user, Question question, Object answer) {
        jdbc.update("insert into user_answer(user_id, question_id, answer, correct) value (?, ?, ?, ?)",
                user.getId(), question.getId(), String.valueOf(answer), question.checkAnswer(answer));
    }

    @Override
    public List<Long> getAnsweredQuestionIds(User user) {
        return jdbc.queryForList("select question_id from user_answer where user_id=?",
                Long.class, user.getId());
    }

    @Override
    public List<BasicAnswerDto> getUserAnswers(User user) {
        return jdbc.query("select question_id, answer from user_answer where user_id=?",
                this::mapRowToMap, user.getId());
    }

    private BasicAnswerDto mapRowToMap (ResultSet rs, int rowNum) throws SQLException {
        return new BasicAnswerDto(
            rs.getLong("question_id"),
            rs.getString("answer")
        );
    }
}
