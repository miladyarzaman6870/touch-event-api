package com.behsa.omni.eventapicall.domain;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class InputModel {
    @NotNull(message = "url can not nulled.")
    @NotEmpty(message = "url can not empty.")
    private String url;
    @NotNull(message = "token can not nulled.")
    @NotEmpty(message = "token can not empty.")
    private String token;
    @NotNull(message = "token can not nulled.")
    @NotEmpty(message = "token can not empty.")
    private String body;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "InputModel{" +
                "url='" + url + '\'' +
                ", token='" + token + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
