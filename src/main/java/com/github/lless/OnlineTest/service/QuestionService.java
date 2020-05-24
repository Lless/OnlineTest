package com.github.lless.OnlineTest.service;

import com.github.lless.OnlineTest.data.QuestionRepository;
import com.github.lless.OnlineTest.data.StatisticsRepository;
import com.github.lless.OnlineTest.data.UserRepository;
import com.github.lless.OnlineTest.dto.BasicAnswerDto;
import com.github.lless.OnlineTest.dto.ExtendedAnswerDto;
import com.github.lless.OnlineTest.domain.Question;
import com.github.lless.OnlineTest.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final UserRepository userRepo;
    private final QuestionRepository questionRepo;
    private final StatisticsRepository statisticsRepository;

    public Question getQuestion(User user) {
        if (user.getCurrentQuestion() != null) return user.getCurrentQuestion();
        List<Long> answeredQuestions = statisticsRepository.getAnsweredQuestionIds(user);
        Question question = questionRepo.findRandomExcept(answeredQuestions);
        user.setCurrentQuestion(question);
        userRepo.setCurrentQuestion(user, question);
        return question;
    }

    public void answerQuestion(User user, Object answer) {
        if (user.getCurrentQuestion() == null)
            throw new RuntimeException("User can't answer question that was not asked");
        Question question = user.getCurrentQuestion();
        user.setCurrentQuestion(null);
        userRepo.removeCurrentQuestion(user);
        statisticsRepository.addAnswer(user, question, answer);
    }

    public List<ExtendedAnswerDto> getUserAnswers(User user) {
        List<BasicAnswerDto> basicDto = statisticsRepository.getUserAnswers(user);
        List<ExtendedAnswerDto> extendedDto = new ArrayList<>();
        basicDto.forEach(dto -> extendedDto.add(new ExtendedAnswerDto(
                questionRepo.findById(dto.getQuestionId()), dto.getAnswer())
        ));
        return extendedDto;
    }
}
