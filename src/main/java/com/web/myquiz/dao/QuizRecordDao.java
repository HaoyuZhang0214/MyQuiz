package com.web.myquiz.dao;

import com.web.myquiz.domain.QuizRecord;

import java.util.List;
import java.util.Optional;

public interface QuizRecordDao {

    List<QuizRecord> getQuizRecordsByUserId(int user_id);

    Optional<QuizRecord> getQuizRecord(int quiz_id, int user_id, int score);

    void createQuizRecord(int quiz_id, int user_id, int score);

    List<QuizRecord> getAllQuizRecords();

    Optional<QuizRecord> getQuizRecordById(int record_id);

}
