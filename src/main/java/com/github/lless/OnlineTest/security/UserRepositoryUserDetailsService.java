package com.github.lless.OnlineTest.security;

import com.github.lless.OnlineTest.data.UserRepository;
import com.github.lless.OnlineTest.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRepositoryUserDetailsService implements UserDetailsService {
    private final UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = repo.findByUsername(username);
        if (user != null) return user;
        throw new UsernameNotFoundException("User " + username + " not found!");
    }
}
