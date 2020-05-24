package com.github.lless.OnlineTest.data;

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
public class UserRepositoryImpl implements UserRepository{
    private final JdbcTemplate jdbc;
    private final QuestionRepository questionRepo;

    @Override
    public User findByUsername(String username) {
        List<User> userList = jdbc.query("select * from User where username=?", this::mapRowToUser, username);
        if (userList.isEmpty()) return null;
        return userList.get(0);
    }

    @Override
    public void save(User user) {
        jdbc.update("insert into user(username, user_password) value (?, ?)", user.getUsername(), user.getPassword());
    }

    @Override
    public void setCurrentQuestion(User user, Question question) {
        jdbc.update("update user set current_question = ? where user.id=?", question.getId(), user.getId());
    }

    @Override
    public void removeCurrentQuestion(User user) {
        jdbc.update("update user set current_question = null where user.id=?", user.getId());
    }

    @Override
    public long userCount() {
        return jdbc.queryForObject("SELECT count(*) FROM onlinetestdb.user", Long.class);
    }

    private User mapRowToUser(ResultSet rs, int rowNum) throws SQLException {
        long id = rs.getLong("current_question");
        Question question = id == 0 ? null : questionRepo.findById(id);
        return new User(rs.getLong("id"),
                rs.getString("username"),
                rs.getString("user_password"),
                question);
    }
}
