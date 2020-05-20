package com.github.lless.OnlineTest.data;

import com.github.lless.OnlineTest.domain.User;

public interface UserRepository {
    User findByUsername(String username);
    void save(User user);
}
