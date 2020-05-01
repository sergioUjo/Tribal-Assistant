package com.example.tribalassistent.data.comunication.notification;

import android.util.Log;

import com.example.tribalassistent.data.comunication.EventMsg;
import com.example.tribalassistent.data.repositories.Observer;
import com.example.tribalassistent.data.repositories.Subject;

import java.util.LinkedList;
import java.util.List;

public class SocketNotification implements Subject<EventMsg> {
    private static final String TAG = "SocketNotification";
    private static SocketNotification instance;
    private List<Observer> observers = new LinkedList<>();

    public static SocketNotification getInstance() {
        if (instance == null) {
            instance = new SocketNotification();
        }
        return instance;
    }

    static void received(EventMsg eventMsg) {
        Log.d(TAG, "Received " + eventMsg.getType());
        //getInstance().notifyObservers(eventMsg);
    }

    @Override
    public void observe(Observer observer) {
        observers.add(observer);
    }


    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            // observer.update(eventMsg);
        }
    }
}
