package com.example.tribalassistent.data.comunication;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import java.io.IOException;

public class JsonParser {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static <T> EventMsg<T> parseEvent(JSONObject jsonObject, Class<T> clazz) {
        try {
            return MAPPER.readValue(jsonObject.toString(), MAPPER.getTypeFactory().constructParametricType(EventMsg.class, clazz));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> JSONObject toJSONObject(T object) {
        try {
            return new JSONObject(MAPPER.writeValueAsString(object));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
