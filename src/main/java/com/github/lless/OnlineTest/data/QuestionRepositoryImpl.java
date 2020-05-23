package com.github.lless.OnlineTest.data;

import com.github.lless.OnlineTest.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.github.lless.OnlineTest.domain.QuestionType.ENTRY_QUESTION;

@Repository
@RequiredArgsConstructor
public class QuestionRepositoryImpl implements QuestionRepository {
    private final JdbcTemplate jdbc;
    private final NamedParameterJdbcTemplate namedJdbc;
    @Override
    public Question findById(Long id) {
        QuestionType type = jdbc.queryForObject("select question_type from question where id=?",
                QuestionType.class, id);
        return findQuestionByInfo(new BasicQuestionInfo(id, type));
    }

    @Override
    public Question findRandomExcept(List<Long> ids) {
        BasicQuestionInfo info;
        if (ids.isEmpty())
            info = jdbc.queryForObject("select * from question order by rand() limit 1",
                    this::mapRowToBasicInfo);
        else {
            Map<String,List<Long>> idsMap = Collections.singletonMap("ids", ids);
            info = namedJdbc.queryForObject("select * from question where id not in (:ids) order by rand() limit 1",
                    idsMap, this::mapRowToBasicInfo);
        }
        return findQuestionByInfo(info);
    }

    private Question findQuestionByInfo(BasicQuestionInfo info) {
        return jdbc.queryForObject("select * from " + info.getType() + " where id=?",
                ENTRY_QUESTION.equals(info.getType()) ? this::mapRowToEntryQuestion : this::mapRowToChoiceQuestion,
                info.getId());
    }
    private EntryQuestion mapRowToEntryQuestion(ResultSet rs, int rowNum) throws SQLException {
        return new EntryQuestion(rs.getLong("id"),
                rs.getString("question"),
                rs.getString("answer"));
    }

    private ChoiceQuestion mapRowToChoiceQuestion(ResultSet rs, int rowNum) throws SQLException {
        return new ChoiceQuestion(rs.getLong("id"),
                rs.getString("question"),
                List.of(
                        rs.getString("option1"),
                        rs.getString("option2"),
                        rs.getString("option3"),
                        rs.getString("option4")
                ),
                rs.getInt("answer"));
    }

    private BasicQuestionInfo mapRowToBasicInfo(ResultSet rs, int rowNum) throws SQLException {
        return new BasicQuestionInfo(rs.getLong("id"),
                QuestionType.valueOf(rs.getString("type")));
    }
}
