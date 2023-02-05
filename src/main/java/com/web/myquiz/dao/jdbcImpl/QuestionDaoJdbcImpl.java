package com.web.myquiz.dao.jdbcImpl;

import com.web.myquiz.dao.QuestionDao;
import com.web.myquiz.domain.Question;
import com.web.myquiz.domain.rowmapper.QuestionRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("questionDaoJdbcImpl")
public class QuestionDaoJdbcImpl implements QuestionDao {

    JdbcTemplate jdbcTemplate;
    QuestionRowMapper questionRowMapper;

    @Autowired
    public QuestionDaoJdbcImpl(JdbcTemplate jdbcTemplate, QuestionRowMapper questionRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.questionRowMapper = questionRowMapper;
    }

    @Override
    public List<Question> getQuestionsByQuiz(int quiz_id) {
        String query = "SELECT * FROM Question WHERE quiz_id = ? and status = ?";
        boolean status = true;
        List<Question> questions = jdbcTemplate.query(query, questionRowMapper, quiz_id, status);
        return questions;
    }

    @Override
    public List<Question> getAllQuestions() {
        String query = "SELECT * FROM Question";
        List<Question> questions = jdbcTemplate.query(query, questionRowMapper);
        return questions;
    }

    @Override
    public void updateQuestionStatus(int question_id) {
        String query = "UPDATE Question SET status = ? WHERE question_id = ?";
        boolean status = true;
        Optional<Question> question = getQuestionById(question_id);
        if(question.isPresent()) {
            status = question.get().isStatus()? false: true;
        }
        jdbcTemplate.update(query, status, question_id);
    }

    @Override
    public Optional<Question> getQuestionById(int question_id) {
        String query = "SELECT * FROM Question WHERE question_id = ?";
        List<Question> questions = jdbcTemplate.query(query, questionRowMapper, question_id);
        return questions.size()==0? Optional.empty(): Optional.ofNullable(questions.get(0));
    }

    @Override
    public void addQuestion(int quiz_id, String content) {
        String query = "INSERT INTO Question (quiz_id, content, status) VALUES (?, ?, ?)";
        jdbcTemplate.update(query,quiz_id, content, true);
    }

    @Override
    public void updateQuestionContent(String content, int question_id) {
        String query = "UPDATE Question SET content = ? WHERE question_id = ?";
        jdbcTemplate.update(query, content, question_id);
    }


}
