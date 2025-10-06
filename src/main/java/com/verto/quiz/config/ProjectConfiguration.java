package com.verto.quiz.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ProjectConfiguration {

    //Create the bean for ModelMapper
    @Bean
    public ModelMapper mapper(){
        return new ModelMapper();
    }
}
