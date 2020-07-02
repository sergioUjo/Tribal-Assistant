package com.example.tribalassistent.data.comunication.request;

import android.util.Log;

import com.example.tribalassistent.client.AsyncCallback;
import com.example.tribalassistent.data.comunication.EventMsg;
import com.example.tribalassistent.data.comunication.EventMsgFactory;
import com.example.tribalassistent.data.comunication.JsonParser;
import com.example.tribalassistent.data.comunication.notification.Notification;
import com.example.tribalassistent.data.model.Requestable;

import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import io.socket.engineio.client.transports.WebSocket;

import static android.os.AsyncTask.THREAD_POOL_EXECUTOR;

public class SocketConnection {
    private static SocketConnection instance;
    private static final String TAG = "SocketConnection";
    private static final String SERVER_URL = "https://pt.tribalwars2.com/";
    private Map<Integer, Request> pendingMessages = new HashMap<>();
    private Socket mSocket;

    private void connect() {
        try {
            IO.Options opts = new IO.Options();
            opts.transports = new String[]{WebSocket.NAME};
            mSocket = IO.socket(SERVER_URL, opts);
            mSocket.on("msg", onMsg);
            mSocket.on("ping", onPing);
            mSocket.on("pong", onPong);
            mSocket.connect();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private SocketConnection() {
        connect();
    }

    public static SocketConnection getInstance() {
        if (instance == null) {
            instance = new SocketConnection();
        }
        return instance;
    }

    private Emitter.Listener onMsg = args -> {
        JSONObject jsonObject = (JSONObject) args[0];
        if (jsonObject.isNull("id")) {
            Log.d(TAG, "Receiving notification: " + jsonObject);
            Notification.received(jsonObject);
        } else {
            Log.d(TAG, "Receiving response: " + jsonObject);
            received(jsonObject);
        }
    };

    private void received(JSONObject jsonObject) {
        Request request = pendingMessages.remove(jsonObject.optInt("id"));
        if (request != null) {
            request.setResponse(jsonObject);
        }
    }

    private Emitter.Listener onPing = args -> Log.i(TAG, "PING");
    private Emitter.Listener onPong = args -> Log.i(TAG, "PONG");


    public <I extends Requestable<O>, O> void send(I request) {
        send(EventMsgFactory.getEvent(request));
    }


    public <I extends Requestable<O>, O> void send(I request, AsyncCallback<O> callback) {
        EventMsg<I> eventMsg = EventMsgFactory.getEvent(request);
        pendingMessages.put(eventMsg.getId(), new Request<>(request, callback));
        send(eventMsg);
    }

    private void send(EventMsg eventMsg) {
        THREAD_POOL_EXECUTOR.execute(() -> {
            JSONObject jsonObject = JsonParser.toJSONObject(eventMsg);
            Log.d(TAG, "Sending: " + jsonObject);
            mSocket.emit("msg", jsonObject);
        });
    }


}
