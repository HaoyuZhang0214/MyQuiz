package com.web.myquiz.service;

import com.web.myquiz.domain.QuestionRecord;

import java.util.List;

public interface QuestionRecordService {

    void createQuestionRecord(int record_id, int question_id, int option_id);

    List<QuestionRecord> getQuestionRecordsByRecord(int record_id);

}
