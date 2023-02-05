package com.web.myquiz.dao;

import com.web.myquiz.domain.Option;

import java.util.List;
import java.util.Optional;

public interface OptionDao {

    List<Option> getOptionsByQuestion(int question_id);

    Optional<Option> getOptionById(int option_id);

    void createOptions(int question_id, List<String> contents, int solution);

    void updateOption(int option_id, String content, boolean is_solution);

}
