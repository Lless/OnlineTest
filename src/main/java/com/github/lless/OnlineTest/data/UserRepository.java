package com.github.lless.OnlineTest.data;

import com.github.lless.OnlineTest.domain.Question;
import com.github.lless.OnlineTest.domain.User;

public interface UserRepository {
    User findByUsername(String username);
    void save(User user);
    void setCurrentQuestion(User user, Question question);
    void removeCurrentQuestion(User user);
}
