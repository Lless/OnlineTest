package com.github.lless.OnlineTest.data;

import com.github.lless.OnlineTest.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository{
    private final JdbcTemplate jdbc;

    @Override
    public User findByUsername(String username) {
        return jdbc.queryForObject("select * from User where username=?", this::mapRowToUser, username);
    }
    private User mapRowToUser(ResultSet rs, int rowNum) throws SQLException {
        return new User(rs.getLong("id"),
                rs.getString("username"),
                rs.getString("user_password"));
    }
}
