package com.github.lless.OnlineTest.controller;

import com.github.lless.OnlineTest.domain.User;
import com.github.lless.OnlineTest.dto.FullStatsDto;
import com.github.lless.OnlineTest.dto.UserStatsDto;
import com.github.lless.OnlineTest.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StatisticsController {
    private final StatisticsService service;

    @GetMapping("/statistics")
    public FullStatsDto getStats() {
        return service.getStats();
    }

    @GetMapping("/myStatistics")
    public UserStatsDto getMyStats(@AuthenticationPrincipal User user) {
        return service.getUserStats(user);
    }
}
