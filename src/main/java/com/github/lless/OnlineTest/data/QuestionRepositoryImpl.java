package com.github.lless.OnlineTest.data;

import com.github.lless.OnlineTest.domain.*;
import com.github.lless.OnlineTest.dto.BasicQuestionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
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
        return findQuestionByInfo(new BasicQuestionDto(id, type));
    }

    @Override
    public Question findRandomExcept(List<Long> ids) {
        BasicQuestionDto dto;
        if (ids.isEmpty())
            dto = jdbc.queryForObject("select * from question order by rand() limit 1",
                    this::mapRowToBasicDto);
        else {
            Map<String,List<Long>> idsMap = Collections.singletonMap("ids", ids);
            dto = namedJdbc.queryForObject("select * from question where id not in (:ids) order by rand() limit 1",
                    idsMap, this::mapRowToBasicDto);
        }
        return findQuestionByInfo(dto);
    }

    @Override
    @Transactional
    public void addQuestion(EntryQuestion question, User author) {
        jdbc.update("insert into Question(question_type, author_id) value ('ENTRY_QUESTION', ?)", author.getId());
        jdbc.update("insert into Entry_Question value (LAST_INSERT_ID(), ?, ?)",
                question.getQuestion(), question.getAnswer());
    }

    @Override
    @Transactional
    public void addQuestion(ChoiceQuestion question, User author) {
        jdbc.update("insert into Question(question_type, author_id) value ('CHOICE_QUESTION', ?)", author.getId());
        List<String> o = question.getOptions();
        jdbc.update("insert into Choice_Question value (LAST_INSERT_ID(), ?, ?, ?, ?, ?, ?)",
                question.getQuestion(), o.get(0), o.get(1), o.get(2), o.get(3), question.getAnswer());
    }

    private Question findQuestionByInfo(BasicQuestionDto dto) {
        return jdbc.queryForObject("select * from " + dto.getType() + " where id=?",
                ENTRY_QUESTION.equals(dto.getType()) ? this::mapRowToEntryQuestion : this::mapRowToChoiceQuestion,
                dto.getId());
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

    private BasicQuestionDto mapRowToBasicDto(ResultSet rs, int rowNum) throws SQLException {
        return new BasicQuestionDto(rs.getLong("id"),
                QuestionType.valueOf(rs.getString("question_type")));
    }
}
