package com.behsa.omni.eventapicall.rest_client;

import com.behsa.omni.eventapicall.common.MicroServiceConstant;
import org.springframework.stereotype.Component;

@Component
public class MicroServiceConstantImpl implements MicroServiceConstant {
    @Override
    public String getServiceVersion() {
        return "1.0.0";
    }

    @Override
    public String getServiceName() {
        return "event-api-call";
    }
}
