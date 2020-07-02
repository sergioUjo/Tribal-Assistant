package com.example.tribalassistent.data.model.system;

import com.example.tribalassistent.data.comunication.request.RequestType;
import com.example.tribalassistent.data.model.Requestable;

import lombok.Data;

@Data
public class Identify implements Requestable<Identified> {
    private String platform = "browser";
    private String device = "Mozilla/5.0%20(Linux;%20Android%208.1.0;%20Redmi%20Note%205)%20AppleWebKit/537.36%20(KHTML,%20like%20Gecko)%20Chrome/81.0.4044.111%20Mobile%20Safari/537.36";
    private String api_version = "10.*.*";

    @Override
    public RequestType getType() {
        return RequestType.SYSTEM_IDENTIFY;
    }

    @Override
    public Class<Identified> getResponse() {
        return Identified.class;
    }
}
