package com.example.tribalassistent.data.model.authentication;

import com.example.tribalassistent.data.comunication.request.RequestType;
import com.example.tribalassistent.data.model.Requestable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Reconnect implements Requestable<Void> {
    private String name;
    private String token;
    private Integer character;
    private String world;

    @Override
    public RequestType getType() {
        return RequestType.AUTH_RECONNECT;
    }

    @Override
    public Class<Void> getResponse() {
        return Void.class;
    }
}
