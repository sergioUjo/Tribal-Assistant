package com.example.tribalassistent.data.comunication;

import com.example.tribalassistent.data.model.Requestable;

public class EventMsgFactory {
    private static int id = 0;

    public static <T extends Requestable> EventMsg<T> getEvent(T data) {
        id++;
        return new EventMsg<>(data, data.getType().toString(), id);
    }
}
