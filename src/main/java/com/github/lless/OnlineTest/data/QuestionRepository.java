package com.github.lless.OnlineTest.data;

import com.github.lless.OnlineTest.domain.ChoiceQuestion;
import com.github.lless.OnlineTest.domain.EntryQuestion;
import com.github.lless.OnlineTest.domain.Question;
import com.github.lless.OnlineTest.domain.User;

import java.util.List;

public interface QuestionRepository {
    Question findById(Long id);
    Question findRandomExcept(List<Long> ids);
    void addQuestion(EntryQuestion question, User author);
    void addQuestion(ChoiceQuestion question, User author);
}
