package com.github.lless.OnlineTest.service;

import com.github.lless.OnlineTest.data.QuestionRepository;
import com.github.lless.OnlineTest.data.UserRepository;
import com.github.lless.OnlineTest.domain.Question;
import com.github.lless.OnlineTest.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final UserRepository userRepo;
    private final QuestionRepository questionRepo;

    public Question getQuestion(User user) {
        if (user.getCurrentQuestion() != null) return user.getCurrentQuestion();
        Question question = questionRepo.findById(1L);
        user.setCurrentQuestion(question);
        userRepo.setCurrentQuestion(user, question);
        return question;
    }

    public void answerQuestion(User user, String answer) {
        if (user.getCurrentQuestion() == null)
            throw new RuntimeException("User can't answer question that was not asked");
        Question question = user.getCurrentQuestion();
        user.setCurrentQuestion(null);
        userRepo.removeCurrentQuestion(user);
        System.out.println(question.checkAnswer(answer));
    }
}
