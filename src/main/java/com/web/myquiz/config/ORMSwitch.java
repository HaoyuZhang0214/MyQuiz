package com.web.myquiz.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ORMSwitch {

    // true: Hibernate, false: JDBC
    @Bean("ormMode")
    public boolean ormMode() {return false;}

}
