package com.example.tribalassistent.client.service.connection;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventMsg<T> {
    private Integer id;
    private T data;
    private String type;
    private TravelTime headers;

    public EventMsg(T data, String type, Integer id) {
        this.id = id;
        this.data = data;
        this.type = type;
        this.headers = new TravelTime();
    }
}