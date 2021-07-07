package com.behsa.omni.eventapicall.rest_client;

import com.behsa.omni.eventapicall.common.EventApiCallException;
import com.behsa.omni.eventapicall.domain.InputModel;
import com.behsa.omni.eventapicall.domain.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class ApiClient {
    public static final Logger LOGGER = LoggerFactory.getLogger(ApiClient.class);
    public int maximumRepeatNumber = 1;
    public int repeatToGetToken = maximumRepeatNumber;
    private RestTemplate restTemplate = new RestTemplate();



    public ResponseModel callApi(InputModel input) {
        HttpHeaders publicHeaders = new HttpHeaders();
        publicHeaders.setContentType(MediaType.APPLICATION_JSON);
        publicHeaders.set("token", input.getToken());
        LOGGER.debug(" this.publicHeaders :  {} ", publicHeaders);
        HttpEntity<String> httpRequest = new HttpEntity<>(input.getBody(), publicHeaders);
        try {
            ResponseEntity<ResponseModel> respEntity = restTemplate.exchange(input.getUrl(),
                    HttpMethod.POST,
                    httpRequest,
                    ResponseModel.class);
            LOGGER.debug("Call: Get new top-up token web service: response {}", respEntity.getBody());
            if (respEntity.getBody() == null) {
                throw new EventApiCallException("error in call ApiCallClient");
            }
            return respEntity.getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() != HttpStatus.UNAUTHORIZED) {
                throw new EventApiCallException("error in call ApiCallClient", e);
            }
            LOGGER.error("********AuthToken expire*******");
            if (repeatToGetToken < 0) {
                repeatToGetToken = maximumRepeatNumber;
                throw new EventApiCallException("error in call ApiCallClient.", e);
            }
            repeatToGetToken--;
            return callApi(input);
        } catch (HttpServerErrorException e) {
            throw new EventApiCallException("error in call ApiCallClient.", e);
        }
    }

}
