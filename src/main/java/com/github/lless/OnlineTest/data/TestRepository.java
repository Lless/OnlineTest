package com.github.lless.OnlineTest.data;

import com.github.lless.OnlineTest.dto.BasicAnswerDto;
import com.github.lless.OnlineTest.domain.Question;
import com.github.lless.OnlineTest.domain.User;

import java.util.List;

public interface TestRepository {
    void addAnswer(User user, Question question, Object answer);
    List<Long> getAnsweredQuestionIds(User user);
    List<BasicAnswerDto> getUserAnswers(User user);
}
