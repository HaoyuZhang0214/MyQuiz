package com.web.myquiz.dao;

import com.web.myquiz.domain.QuestionRecord;

import java.util.List;

public interface QuestionRecordDao {

    void createQuestionRecord(int record_id, int question_id, int option_id);

    List<QuestionRecord> getQuestionRecordsByRecord(int record_id);

}
