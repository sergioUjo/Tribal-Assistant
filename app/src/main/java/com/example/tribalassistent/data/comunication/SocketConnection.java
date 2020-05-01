package com.example.tribalassistent.data.comunication;

import android.util.Log;

import com.example.tribalassistent.data.comunication.notification.Notification;
import com.example.tribalassistent.data.comunication.request.SocketRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import io.socket.engineio.client.transports.WebSocket;

public class SocketConnection {
    private static final String TAG = "SocketConnection";
    public static final String SERVER_URL = "https://pt.tribalwars2.com/";
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static Socket mSocket;

    public static void init() {
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

    private static Emitter.Listener onMsg = args -> {
        JSONObject jsonObject = (JSONObject) args[0];

        if (jsonObject.isNull("id")) {
            Log.d(TAG, "Receiving notification: " + jsonObject);
            Notification.received(jsonObject);
        } else {
            Log.d(TAG, "Receiving reponse: " + jsonObject);
            SocketRequest.received(jsonObject);
        }
    };

    public static void sendDataToServer(EventMsg eventMsg) {
        JSONObject jsonObject = getJSON(eventMsg);
        Log.d(TAG, "Sending: " + jsonObject);
        mSocket.emit("msg", jsonObject);
    }


    private static Emitter.Listener onPing = args -> Log.i(TAG, "PING");
    private static Emitter.Listener onPong = args -> Log.i(TAG, "PONG");


    private static JSONObject getJSON(EventMsg eventMsg) {
        try {
            return new JSONObject(MAPPER.writeValueAsString(eventMsg));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
