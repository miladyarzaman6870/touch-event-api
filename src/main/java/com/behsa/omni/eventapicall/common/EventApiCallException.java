package com.behsa.omni.eventapicall.common;

public class EventApiCallException extends RuntimeException {
    public EventApiCallException(String s) {
        super(s);
    }

    public EventApiCallException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
