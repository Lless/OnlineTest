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
        Integer answeredQuestions = repo.getAnsweredQuestionsCount(user);
        return new UserStatsDto(
                (float)correctAnswers /
                        ( answeredQuestions > 0 ? answeredQuestions : 1) ,
                (float)repo.getUsersWithMoreCorrectAnswersCount(correctAnswers) /
                        ((started > 2) ? (started-1) : started),
                (float)repo.getUsersWithLessCorrectAnswersCount(correctAnswers) /
                        ((started > 2) ? (started-1) : started)
        );
    }
}
