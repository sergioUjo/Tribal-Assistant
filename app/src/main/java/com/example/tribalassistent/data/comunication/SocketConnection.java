package com.example.tribalassistent.data.comunication;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import io.socket.engineio.client.transports.WebSocket;

public class SocketConnection {

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
            mSocket.on(Socket.EVENT_CONNECT, onConnect);
            mSocket.on(Socket.EVENT_DISCONNECT, onDisconnect);
            mSocket.on(Socket.EVENT_CONNECT_ERROR, onConnectError);
            mSocket.on(Socket.EVENT_CONNECT_TIMEOUT, onConnectTimeout);
            mSocket.connect();

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

    private static Emitter.Listener onMsg = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JSONObject jsonObject = (JSONObject) args[0];
            System.out.println(jsonObject);
            MessagerSync.getInstance().received(getEventMsg(jsonObject));
        }
    };

    public static void sendDataToServer(EventMsg eventMsg) {
        JSONObject jsonObject = getJSON(eventMsg);
        System.out.println(jsonObject);
        mSocket.emit("msg", jsonObject);
    }

    private static Emitter.Listener onConnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
        }
    };
    private static Emitter.Listener onPing = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            System.out.println("PING");
        }
    };
    private static Emitter.Listener onPong = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            System.out.println("PONG");
        }
    };
    private static Emitter.Listener onDisconnect = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            System.out.println("Disconnected");
        }
    };
    private static Emitter.Listener onConnectError = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            System.out.println("Connecting Error");
        }
    };
    private static Emitter.Listener onConnectTimeout = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            System.out.println("Timeout");
        }
    };

    private static <T> JSONObject getJSON(EventMsg<T> eventMsg) {
        try {
            return new JSONObject(MAPPER.writeValueAsString(eventMsg));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static EventMsg getEventMsg(JSONObject jsonObject) {
        String type = jsonObject.optString("type");
        return getEventMsg(EventType.fromString(type).getClazz(), jsonObject);
    }

    private static <T> EventMsg<T> getEventMsg(Class<T> clazz, JSONObject jsonObject) {
        try {
            return MAPPER.readValue(jsonObject.toString(), MAPPER.getTypeFactory().constructParametricType(EventMsg.class, clazz));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
