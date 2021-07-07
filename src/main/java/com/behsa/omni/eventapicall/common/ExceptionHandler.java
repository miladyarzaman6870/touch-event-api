package com.behsa.omni.eventapicall.common;

import com.fasterxml.jackson.core.JsonParseException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.xml.bind.DataBindingException;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(JsonParseException.class)
    private void exception1(Exception e){
        System.out.println("lllllllll"+e.getMessage());
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(EventApiCallException.class)
    private void exception2(Exception e){
        System.out.println("lllllllll"+e.getMessage());
        throw new RuntimeException();
    }
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    private void exception3(Exception e){
        System.out.println("lllllllll"+e.getMessage());
    }
}
