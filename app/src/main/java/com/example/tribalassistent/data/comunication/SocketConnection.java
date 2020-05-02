package com.example.tribalassistent.data.comunication;

import android.util.Log;

import com.example.tribalassistent.data.comunication.notification.Notification;
import com.example.tribalassistent.data.comunication.request.SocketRequest;

import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import io.socket.engineio.client.transports.WebSocket;

public class SocketConnection {
    private static final String TAG = "SocketConnection";
    private static final String SERVER_URL = "https://pt.tribalwars2.com/";
    private static SocketConnection instance;
    private Socket mSocket;

    private SocketConnection() {
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
            Log.d(TAG, "Receiving reponse: " + jsonObject);
            SocketRequest.received(jsonObject);
        }
    };

    public void sendDataToServer(EventMsg eventMsg) {
        JSONObject jsonObject = JsonParser.toJSONObject(eventMsg);
        Log.d(TAG, "Sending: " + jsonObject);
        mSocket.emit("msg", jsonObject);
    }


    private Emitter.Listener onPing = args -> Log.i(TAG, "PING");
    private Emitter.Listener onPong = args -> Log.i(TAG, "PONG");


}
