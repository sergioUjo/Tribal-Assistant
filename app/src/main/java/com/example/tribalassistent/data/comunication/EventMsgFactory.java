package com.example.tribalassistent.data.comunication;

import com.example.tribalassistent.data.comunication.request.RequestType;

public class EventMsgFactory {
    private static int id = 0;

    public static <T> EventMsg<T> getEvent(T data, RequestType requestType) {
        id++;
        return new EventMsg<>(data, requestType.getType(), id);
    }
}
