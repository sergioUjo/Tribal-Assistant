package com.example.tribalassistent.data.comunication.request;

import com.example.tribalassistent.data.comunication.EventMsg;
import com.example.tribalassistent.data.comunication.JsonParser;
import com.example.tribalassistent.data.comunication.SocketConnection;
import com.example.tribalassistent.data.model.system.Error;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.os.AsyncTask.THREAD_POOL_EXECUTOR;

public abstract class SocketRequest<I, O> implements Runnable {
    private static Map<Integer, SocketRequest> pendingMessages = new HashMap<>();
    private OnResultListener<O> resultListener;

    public void onResultListener(OnResultListener<O> resultListener) {
        this.resultListener = resultListener;
    }

    public void doInBackground() {
        THREAD_POOL_EXECUTOR.execute(this);
    }

    @Override
    public void run() {
        if (resultListener != null) {
            pendingMessages.put(getRequest().getId(), this);
        }
        SocketConnection.sendDataToServer(getRequest());
    }

    private void onPostExecute(JSONObject jsonObject) {
        Result<O> result;
        String type = jsonObject.optString("type");
        if (ErrorType.fromString(type) != null) {
            result = new Result<>(JsonParser.parseEvent(jsonObject, Error.class).getData());
        } else {
            result = new Result<>(JsonParser.parseEvent(jsonObject, getClazz()).getData());
        }
        resultListener.onResult(result);
    }

    public static void received(JSONObject jsonObject) {
        SocketRequest socketRequest = pendingMessages.get(jsonObject.optInt("id"));
        if (socketRequest != null) {
            socketRequest.onPostExecute(jsonObject);
        }
    }

    abstract Class<O> getClazz();

    abstract EventMsg<I> getRequest();

}
