package com.behsa.omni.eventapicall.domain;


public class LoggerModel  {
    private String inpoutMsg;
    private String responseBody;
    private String responseDesc;
    private String responseType;
    private String ip;
    private String stopWatch;
    private String startTime;
    private String logLevel;


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


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
    public String getInpoutMsg() {
        return inpoutMsg;
    }

    public void setInpoutMsg(String inpoutMsg) {
        this.inpoutMsg = inpoutMsg;
    }

    public String getStopWatch() {
        return stopWatch;
    }

    public void setStopWatch(String stopWatch) {
        this.stopWatch = stopWatch;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
