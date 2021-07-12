package com.behsa.omni.eventapicall;

import com.behsa.omni.eventapicall.consumer.EventConsumer;
import com.behsa.omni.eventapicall.domain.InputModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class EventApiCallApplicationTests {
    @Autowired
    EventConsumer eventConsumer;
    @Autowired
    ObjectMapper mapper;
    @Test
    void contextLoads() throws JsonProcessingException {
        System.out.println("//////////////////");
        InputModel inputModel=new InputModel();
        inputModel.setUrl("hhgjhjhj");
        inputModel.setToken("sfdfg");
        inputModel.setBody("bhjghjghjg");
        String s = mapper.writeValueAsString(inputModel);
        eventConsumer.onRecive(s);
    }
}
