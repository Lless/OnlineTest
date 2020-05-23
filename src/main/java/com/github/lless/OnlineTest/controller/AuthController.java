package com.github.lless.OnlineTest.controller;

import com.github.lless.OnlineTest.data.UserRepository;
import com.github.lless.OnlineTest.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class AuthController {
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;

    @PostMapping("/login")
    public boolean login(@RequestBody User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());
        return encoder.matches(user.getPassword(), userFromDb.getPassword());
    }

    @PostMapping("/register")
    public boolean register(@RequestBody User user) {
        if (userRepo.findByUsername(user.getUsername()) != null) return false;
        User userWithEncodedPassword = new User(user.getUsername(), encoder.encode(user.getPassword()));
        userRepo.save(userWithEncodedPassword);
        return true;
    }
}
