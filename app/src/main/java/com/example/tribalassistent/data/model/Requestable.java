package com.example.tribalassistent.data.model;

import com.example.tribalassistent.data.comunication.request.RequestType;

public interface Requestable<T> {
    RequestType getType();

    Class<T> getResponse();
}
