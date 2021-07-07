package com.behsa.omni.eventapicall.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Validator;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;

@Configuration
public class BeanConfig {

    @Bean
    ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
    @Bean
    public javax.validation.Validator getValidator(){
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            return factory.getValidator();

    }
}
