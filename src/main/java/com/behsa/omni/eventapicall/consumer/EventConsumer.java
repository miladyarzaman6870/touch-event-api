package com.behsa.omni.eventapicall.consumer;

import com.behsa.omni.eventapicall.common.EventApiCallException;
import com.behsa.omni.eventapicall.common.EventApiCallValidationException;
import com.behsa.omni.eventapicall.domain.InputModel;
import com.behsa.omni.eventapicall.domain.ResponseModel;
import com.behsa.omni.eventapicall.producer.EventApiCallProducer;
import com.behsa.omni.eventapicall.rest_client.ApiClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.Date;
import java.util.Set;
import java.util.function.Consumer;

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
    ResponseModel responseModel=new ResponseModel();
    InputModel inputModel;
    StringBuilder resDesc = new StringBuilder();
    @RabbitListener(queues = "${queueName1}")
    public void onRecive(String inputMsg) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        long startTime = new Date().getTime();
        try {
            inputModel = mapper.readValue(inputMsg, InputModel.class);
            validateInputModel(inputModel);
            responseModel = client.callApi(inputModel);
            responseModel.setResponseDesc("successFully");
            responseModel.setResponseType("0");
        } catch (IOException e) {
            setResponseModel("can not convert from json to object. your input is not valid.","2015");
        }catch (EventApiCallValidationException e){
             setResponseModel(e.getMessage(),e.getErrorCode());
        }catch (EventApiCallException e){
            setResponseModel(e.getMessage(),e.getErrorCode());
        }catch(Exception e){
            setResponseModel("unKnown Error.","2016");
        }
        producer.sendLogMessage(inputMsg,responseModel,String.valueOf(startTime),String.valueOf(stopWatch.getTime()));
        producer.sendMessage(responseModel);
    }

        private void validateInputModel(InputModel inputModel) {
        Consumer<ConstraintViolation<InputModel>> consumer = (s) -> {
            resDesc.append(s.getMessage());
        };
        Set<ConstraintViolation<InputModel>> violations = validator.validate(inputModel);
        if (!violations.isEmpty()) {
            violations.forEach(consumer);
            throw new EventApiCallValidationException(resDesc.toString(),"2010");
        }
    }
    private void setResponseModel(String desc, String type) {
        responseModel.setResponseDesc(desc);
        responseModel.setResponseType(type);
    }
}
