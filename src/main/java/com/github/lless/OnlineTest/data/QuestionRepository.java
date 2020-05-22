package com.github.lless.OnlineTest.data;

import com.github.lless.OnlineTest.domain.Question;

import java.util.List;

public interface QuestionRepository {
    Question findById(Long id);
    Question findRandomExcept(List<Long> ids);
}
