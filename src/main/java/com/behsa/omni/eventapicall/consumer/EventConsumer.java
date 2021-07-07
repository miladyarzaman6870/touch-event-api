package com.behsa.omni.eventapicall.consumer;

import com.behsa.omni.eventapicall.common.EventApiCallException;
import com.behsa.omni.eventapicall.domain.InputModel;
import com.behsa.omni.eventapicall.domain.ResponseModel;
import com.behsa.omni.eventapicall.producer.EventApiCallProducer;
import com.behsa.omni.eventapicall.rest_client.ApiClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Service
public class EventConsumer {
    private static final Logger logger = LoggerFactory.getLogger(EventConsumer.class);
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private ApiClient client;
    @Autowired
    EventApiCallProducer producer;
    @Qualifier("getValidator")
    @Autowired
    Validator validator;
    String temp;
    String resDesc = "";
    @RabbitListener(queues = "${queue-name}")
    public void listen(String msg) {
        logger.info(msg);
        ResponseModel responseModel = client.callApi(mapToInputModel(msg));
        producer.sendMessage(responseModel);
    }

    private InputModel mapToInputModel(String msg) {
        InputModel input=null;


        Consumer<String> consumer = (s) -> {
            System.out.println(s+".....");
            temp=resDesc.concat(s);
            resDesc=temp;
        };
        try {
            input = mapper.readValue(msg, InputModel.class);
            Set<ConstraintViolation<InputModel>> violations = validator.validate(input);
            if (violations.stream().iterator().hasNext()) {
                consumer.accept(violations.stream().iterator().next().getMessage());
            }
            throw new EventApiCallException(resDesc.toString());
        } catch (IOException e) {
            logger.error("objectMapper error:", e);
        }
        return input;
    }
}
