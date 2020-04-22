package com.example.tribalassistent.data.comunication;

public class EventMsgFactory {
    private static int id = 0;

    public static <T> EventMsg<T> getEvent(T data, EventType eventType) {
        id++;
        return new EventMsg<>(data, eventType.getType(), id);
    }
}
