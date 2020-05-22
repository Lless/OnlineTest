package com.github.lless.OnlineTest.data;

import com.github.lless.OnlineTest.domain.EntryQuestion;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@RequiredArgsConstructor
public class QuestionRepositoryImpl implements QuestionRepository {
    private final JdbcTemplate jdbc;

    @Override
    public EntryQuestion findById(Long id) {
        return jdbc.queryForObject("select * from entry_question where id=?",
                this::mapRowToEntryQuestion, id);
    }

    private EntryQuestion mapRowToEntryQuestion(ResultSet rs, int rowNum) throws SQLException {
        return new EntryQuestion(rs.getLong("id"),
                rs.getString("question"),
                rs.getString("answer"));
    }
}
