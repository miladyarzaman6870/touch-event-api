package com.behsa.omni.eventapicall;

import com.behsa.omni.eventapicall.common.MicroServiceConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EventApiCallApplication implements CommandLineRunner {
    public static final Logger  LOGGER= LoggerFactory.getLogger(EventApiCallApplication.class);
    @Autowired
    MicroServiceConstant microServiceConstant;
    public static void main(String[] args) {
        SpringApplication.run(EventApiCallApplication.class, args);
    }
    @Override
    public void run(String... args)  {
        LOGGER.info("serviceName: {} version:{}  started successfully.",microServiceConstant.getServiceName(),microServiceConstant.getServiceVersion());
    }
}
