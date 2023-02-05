package com.web.myquiz.service.impl;

import com.web.myquiz.dao.OptionDao;
import com.web.myquiz.domain.Option;
import com.web.myquiz.service.OptionService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OptionServiceImpl implements OptionService {

    private OptionDao optionDao;
    private final boolean ormMode;

    public OptionServiceImpl(@Qualifier("optionDaoJdbcImpl") OptionDao optionDaoJdbcImpl,
                             @Qualifier("optionDaoHibernateImpl") OptionDao optionDaoHibernateImpl,
                             @Qualifier("ormMode") boolean ormMode) {
        this.ormMode = ormMode;
        this.optionDao = ormMode ? optionDaoHibernateImpl: optionDaoJdbcImpl;
    }

    @Override
    public List<Option> getOptionsByQuestion(int question_id) {
        return optionDao.getOptionsByQuestion(question_id);
    }

    @Override
    public Optional<Option> getOptionById(int option_id) {
        return optionDao.getOptionById(option_id);
    }

    @Override
    public void createOptions(int question_id, List<String> contents, int solution) {
        optionDao.createOptions(question_id, contents, solution);
    }

    @Override
    public void updateOption(int option_id, String content, boolean is_solution) {
        optionDao.updateOption(option_id, content, is_solution);
    }
}
