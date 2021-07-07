package com.behsa.omni.eventapicall.domain;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class ResponseModel {
    @NotNull(message = "responseBody can not null.")
    private String responseBody;
    private String responseDesc;
    private String responseType;

    public String getResponseBody() {
        return responseBody;
    }
    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public String getResponseDesc() {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }

    public String getResponseType() {
        return responseType;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }
}
