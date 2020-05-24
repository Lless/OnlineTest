package com.github.lless.OnlineTest.service;

import com.github.lless.OnlineTest.data.StatisticsRepository;
import com.github.lless.OnlineTest.data.UserRepository;
import com.github.lless.OnlineTest.domain.User;
import com.github.lless.OnlineTest.dto.FullStatsDto;
import com.github.lless.OnlineTest.dto.UserStatsDto;
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

    public UserStatsDto getUserStats(User user) {
        Integer correctAnswers = repo.getCorrectAnswersCount(user);
        Long started = repo.getStartedTestUserCount();
        return new UserStatsDto(
                (float)correctAnswers/repo.getAnsweredQuestionsCount(user),
                (float)repo.getUsersWithMoreCorrectAnswersCount(correctAnswers)/(started-1),
                (float)repo.getUsersWithLessCorrectAnswersCount(correctAnswers)/(started-1)
        );
    }
}
