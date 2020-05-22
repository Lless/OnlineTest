package com.github.lless.OnlineTest.data;

import com.github.lless.OnlineTest.domain.EntryQuestion;
import com.github.lless.OnlineTest.domain.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class QuestionRepositoryImpl implements QuestionRepository {
    private final JdbcTemplate jdbc;
    private final NamedParameterJdbcTemplate namedJdbc;
    @Override
    public EntryQuestion findById(Long id) {
        return jdbc.queryForObject("select * from entry_question where id=?",
                this::mapRowToEntryQuestion, id);
    }

    @Override
    public Question findRandomExcept(List<Long> ids) {
        Map idsMap = Collections.singletonMap("ids", ids);
        return namedJdbc.queryForObject("select * from entry_question where id not in (:ids) order by rand() limit 1",
                idsMap, this::mapRowToEntryQuestion);
    }

    private EntryQuestion mapRowToEntryQuestion(ResultSet rs, int rowNum) throws SQLException {
        return new EntryQuestion(rs.getLong("id"),
                rs.getString("question"),
                rs.getString("answer"));
    }
}
