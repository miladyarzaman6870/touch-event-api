package com.behsa.omni.eventapicall;

import com.behsa.omni.eventapicall.domain.InputModel;
import com.behsa.omni.eventapicall.domain.LoggerModel;
import com.behsa.omni.eventapicall.domain.ResponseModel;
import com.behsa.omni.eventapicall.mockserver.MockServerRequest;
import com.behsa.omni.eventapicall.mockserver.MockServerResponse;
import com.behsa.omni.eventapicall.producer.Producer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.junit.jupiter.api.*;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.matchers.Times.exactly;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.mockserver.model.JsonBody.json;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MockServer {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private ClientAndServer mockServer;
    private InputModel inputModel;
    @Autowired
    Producer producer;
    @Autowired
    ObjectMapper mapper;
    ResponseModel responseModelfromfinalConsumer;
    LoggerModel loggerModelFromLoggerConsumer;
    @Value("${exchangeName1}")
    private String exchangeName1;
    @Value("${routingKey1}")
    private String routingKey1;
    @BeforeAll
    public void startServer() {
        while (mockServer == null) {
            try {
                mockServer = startClientAndServer(RandomValueGenerator.randomIntBetwenAandB(3000, 65000));
            } catch (RuntimeException e) {
                logger.warn("in RestApiBusinessTest:  can not start mockServer becuse :", e);
            }
        }
    }

    @AfterEach
    public void afterEach() {
        inputModel = null;
            }

    @Test
    void testValidData() throws IOException, InterruptedException {
        inputModel = RandomValueGenerator.getRandomValidInput();
        inputModel.setUrl(inputModel.getUrl().concat(mockServer.getPort().toString()));
        matcher();
        producer.sendMessage(inputModel,exchangeName1,routingKey1);
         Thread.sleep(5000);
         Assertions.assertEquals("0",responseModelfromfinalConsumer.getResponseType());
    }
    @RabbitListener(queues = "${queueName2}")
    public void onRecive(String inputMsg) throws IOException {
        responseModelfromfinalConsumer = mapper.readValue(inputMsg, ResponseModel.class);
        logger.info("all thing is ok, {}",inputMsg);
    }
    @RabbitListener(queues = "${queueName3}")
    public void loggerOnRecive(String inputMsg) throws IOException {
        loggerModelFromLoggerConsumer=mapper.readValue(inputMsg, LoggerModel.class);
    }
    public void matcher() throws JsonProcessingException {
        MockServerRequest request = new MockServerRequest();
        request.setBody(inputModel.getBody());
        MockServerResponse response = new MockServerResponse();
        response.setResponseBody("milad response Body");
        ObjectMapper objectMapper=new ObjectMapper();
        mockServer.when(
                request()
                        .withMethod("POST")
                        .withPath("/")
                        .withHeader("\"Content-type\", \"application/json\"")
                        .withBody(
                                request.getBody()),
                exactly(1))
                .respond(
                        response()
                                .withStatusCode(200)
                                .withHeaders(
                                        new Header("Content-Type", "application/json; charset=utf-8"),
                                        new Header("Cache-Control", "public, max-age=86400"))
                                .withBody(json(objectMapper.writeValueAsString(response)))
                                .withDelay(TimeUnit.SECONDS, 1)
                );
    }
}