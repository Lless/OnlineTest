package com.github.lless.OnlineTest.service;

import com.github.lless.OnlineTest.data.StatisticsRepository;
import com.github.lless.OnlineTest.data.UserRepository;
import com.github.lless.OnlineTest.dto.FullStatsDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final UserRepository userRepo;
    private final StatisticsRepository repo;
    public FullStatsDto getStats() {
        return new FullStatsDto(
                userRepo.userCount(),
                repo.getStartedTestUserCount(),
                repo.getFinishedTestUserCount(),
                repo.getAllCorrectAnsweredUserCount()
        );
    }
}
