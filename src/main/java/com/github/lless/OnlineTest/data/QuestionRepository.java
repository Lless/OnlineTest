package com.github.lless.OnlineTest.data;

import com.github.lless.OnlineTest.domain.Question;

public interface QuestionRepository {
    Question findById(Long id);
}
