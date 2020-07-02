package com.example.tribalassistent.data.model;

import com.example.tribalassistent.client.service.connection.RequestType;

public interface Requestable<T> {
    RequestType getType();

    Class<T> getResponse();
}
