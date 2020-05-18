package com.github.lless.OnlineTest.data;

import com.github.lless.OnlineTest.domain.EntryQuestion;

public interface EntryQuestionRepository {
    EntryQuestion findById(Long id);
}
