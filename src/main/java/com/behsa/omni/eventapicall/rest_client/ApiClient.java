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
    private RestTemplate restTemplate = new RestTemplate();

    public ResponseModel callApi(InputModel input) {
        HttpHeaders publicHeaders = new HttpHeaders();
        ResponseEntity<ResponseModel> respEntity=null;
        publicHeaders.setContentType(MediaType.APPLICATION_JSON);
        publicHeaders.set("token", input.getToken());
        LOGGER.debug(" this.publicHeaders :  {} ", publicHeaders);
        HttpEntity<String> httpRequest = new HttpEntity<>(input.getBody(), publicHeaders);
        try {
             respEntity = restTemplate.exchange(input.getUrl(),
                    HttpMethod.POST,
                    httpRequest,
                    ResponseModel.class);
            System.out.println("////////1");
            LOGGER.debug("Call: Get new top-up token web service: response {}", respEntity.getBody());
            if (respEntity.getBody() == null) {
                throw new EventApiCallException("error in call ApiCallClient","2011");
            }
        } catch (HttpClientErrorException e) {
            System.out.println("////////3");
            if (e.getStatusCode() != HttpStatus.UNAUTHORIZED) {
                throw new EventApiCallException("error in call ApiCallClient.(UNAUTHORIZED)","2012", e);
            }
            throw new EventApiCallException("error in call ApiCallClient.","2018", e);
        } catch (HttpServerErrorException e) {
            System.out.println("////////5");
            throw new EventApiCallException("error in call ApiCallClient.(httpServerException)", "2013",e);
        }catch (Exception e){
            System.out.println("////////6");
            throw new EventApiCallException("error in call ApiCallClient.", "2014",e);
        }
        return respEntity.getBody();
    }

}
