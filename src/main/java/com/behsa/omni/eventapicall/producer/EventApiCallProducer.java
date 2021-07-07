package com.behsa.omni.eventapicall.producer;

import com.behsa.omni.eventapicall.domain.ResponseModel;
import com.behsa.usdp.ServiceEventDeliver;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EventApiCallProducer {
    @Autowired
    private  RabbitTemplate template;
    @Value("${exchange-name}")
    private String xname;
    @Value("${routingKey}")
    private String routingKey;
    private ObjectMapper mapper = new ObjectMapper();

    public void sendMessage(ResponseModel responseModel) {
        template.convertAndSend(xname, routingKey, responseModel);
//        try {
//            String json = mapper.writeValueAsString(responseModel);
//            template.convertAndSend(xname, routingKey, json);
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }
    }

}
