package com.behsa.omni.eventapicall.common;

public class EventApiCallValidationException extends RuntimeException{
    private String errorCode;
    public EventApiCallValidationException(String s,String errorCode) {
        super(s);
        this.errorCode=errorCode;
    }

    public EventApiCallValidationException(String s,String errorCode ,Throwable throwable) {
        super(s, throwable);
        this.errorCode=errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
