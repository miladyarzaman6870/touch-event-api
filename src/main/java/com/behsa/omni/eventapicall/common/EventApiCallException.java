package com.behsa.omni.eventapicall.common;

public class EventApiCallException extends RuntimeException {
    private String errorCode;
    public EventApiCallException(String s,String errCode) {
        super(s);
        this.errorCode=errCode;
    }

    public EventApiCallException(String s,String errCode, Throwable throwable) {
        super(s, throwable);
        this.errorCode=errCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
