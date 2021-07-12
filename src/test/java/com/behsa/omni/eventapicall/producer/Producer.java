package com.behsa.omni.eventapicall.producer;

import com.behsa.omni.eventapicall.domain.InputModel;
import com.behsa.usdp.ServiceEventDeliver;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service

public class Producer {
    @Autowired
    private  RabbitTemplate template;
    @Autowired
    private ObjectMapper mapper;
    public void sendMessage(InputModel  inputModel, String xchangeName, String routingKey) throws IOException {
            String json = mapper.writeValueAsString(inputModel);
            template.convertAndSend(xchangeName, routingKey, json);
    }

}
