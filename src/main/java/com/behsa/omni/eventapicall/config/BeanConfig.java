package com.behsa.omni.eventapicall.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.net.InetAddress;

@Configuration
public class BeanConfig {
    private static final Logger LOGGER= LoggerFactory.getLogger(BeanConfig.class);

    @Bean
    ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
    @Bean
    public javax.validation.Validator getValidator(){
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            return factory.getValidator();
    }
    @Bean
    public String getIp(){
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (Exception ex) {
            LOGGER.error("Error occurred in getHostIp :{}", ex);
            return null;
        }
    }
}
