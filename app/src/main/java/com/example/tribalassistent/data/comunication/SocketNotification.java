package com.example.tribalassistent.data.comunication;

import android.util.Log;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class SocketNotification implements Subject {
    private static final String TAG = "SocketNotification";
    private static SocketNotification instance;
    private Map<String, EventMsg> serverNotifications = new HashMap<>();
    private List<Observer> observers = new LinkedList<>();

    public static SocketNotification getInstance() {
        if (instance == null) {
            instance = new SocketNotification();
        }
        return instance;
    }

    static void received(EventMsg eventMsg) {
        Log.d(TAG, "Received " + eventMsg.getType());
        SocketNotification socketNotification = getInstance();
        socketNotification.serverNotifications.put(eventMsg.getType(), eventMsg);
        socketNotification.notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public Object getEvent(EventType eventType) {
        EventMsg result = serverNotifications.remove(eventType.getType());
        return result == null ? null : result.getData();
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}
