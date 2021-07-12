package com.behsa.omni.eventapicall.producer;


import com.behsa.omni.eventapicall.domain.LoggerModel;
import com.behsa.omni.eventapicall.domain.ResponseModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EventApiCallProducer {
    private static Logger logger=LoggerFactory.getLogger(EventApiCallProducer.class);
    @Autowired
    private  RabbitTemplate template;
    @Autowired
    private String ip;
    @Autowired
    ObjectMapper mapper;
    @Value("${exchangeName2}")
    private String exchangeName2;
    @Value("${routingKey2}")
    private String routingKey2;
    @Value("${exchangeName3}")
    private String exchangeName3;
    @Value("${routingKey3}")
    private String routingKey3;
    public void sendMessage(ResponseModel responseModel) {
        try {
        template.convertAndSend(exchangeName2, routingKey2,mapper.writeValueAsString( responseModel));
    } catch (Exception e) {
        logger.error("error in EventApiCallProducer:",e);
    }
    }
    public void sendLogMessage(String inputMsg, ResponseModel responseModel, String startDate, String stopWatch) {
        try {
            template.convertAndSend(exchangeName3,routingKey3,mapper.writeValueAsString(getLogMessage(inputMsg,responseModel,startDate,stopWatch)));
        } catch (Exception e) {
            logger.error("error in EventApiCallProducer:",e);
        }
    }
    private LoggerModel getLogMessage(String inputMsg, ResponseModel responseModel, String startTime, String stopWatch) {
        LoggerModel loggerModel=new LoggerModel();
        loggerModel.setInpoutMsg(inputMsg);
        loggerModel.setResponseType(responseModel.getResponseType());
        loggerModel.setResponseDesc(responseModel.getResponseDesc());
        loggerModel.setResponseBody(responseModel.getResponseBody());
        loggerModel.setIp(ip);
        loggerModel.setStartTime(startTime);
        loggerModel.setStopWatch(stopWatch);
        loggerModel.setLogLevel(responseModel.getResponseType().equals("0")?"info":"error");
        return loggerModel;
    }
}
