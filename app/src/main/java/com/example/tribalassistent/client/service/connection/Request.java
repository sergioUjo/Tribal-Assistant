package com.example.tribalassistent.client.service.connection;

import com.example.tribalassistent.client.AsyncCallback;
import com.example.tribalassistent.client.service.connection.util.JsonParser;
import com.example.tribalassistent.data.model.Requestable;
import com.example.tribalassistent.data.model.system.Error;

import org.json.JSONObject;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
class Request<O> {
    private Requestable<O> request;
    private AsyncCallback<O> callback;

    void setResponse(JSONObject jsonObject) {
        if (callback == null) return;

        String type = jsonObject.optString("type");
        if (ErrorType.fromString(type) != null) {
            callback.onFailure(JsonParser.parseEvent(jsonObject, Error.class).getData());
        } else {
            callback.onSuccess(JsonParser.parseEvent(jsonObject, request.getResponse()).getData());
        }
    }
}
