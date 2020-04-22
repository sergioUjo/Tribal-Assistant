package com.example.tribalassistent.data.comunication;

import android.os.AsyncTask;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MessagerSync extends AsyncTask<EventMsg, Result, Result> implements Subject {
    private static MessagerSync instance = null;
    private List<Integer> pendingMessages = new LinkedList<>();
    private Map<Integer, EventMsg> serverResponse = new HashMap<>();
    private Map<String, Object> serverNotifications = new HashMap<>();
    private List<Observer> observers = new LinkedList<>();

    private MessagerSync() {
    }

    public static MessagerSync getInstance() {
        if (instance == null)
            instance = new MessagerSync();

        return instance;
    }

    @Override
    protected Result doInBackground(EventMsg... eventMsgs) {
        EventMsg eventMsg = eventMsgs[0];
        Integer id = eventMsg.getId();
        pendingMessages.add(id);
        SocketConnection.sendDataToServer(eventMsg);
        synchronized (id) {
            try {
                id.wait();
                return getResponse(id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static Result send(EventMsg eventMsg) {
        return getInstance().doInBackground(eventMsg);
    }

    public static <T> Result send(T data, EventType eventType) {
        return send(EventMsgFactory.getEvent(data, eventType));
    }

    public void received(EventMsg eventMsg) {
        Integer id = eventMsg.getId();
        if (id == null) {
            serverNotifications.put(eventMsg.getType(), eventMsg.getData());
            notifyObservers();
        } else {
            int index = pendingMessages.indexOf(id);
            id = pendingMessages.remove(index);
            serverResponse.put(id, eventMsg);
            synchronized (id) {
                id.notify();
            }
        }
    }

    private Result getResponse(int id) {
        return getResponse(serverResponse.remove(id));
    }

    private static Result getResponse(EventMsg eventMsg) {
        if (eventMsg.getData() instanceof Error) {
            return new Result.Error(((Error) eventMsg.getData()).getMessage());
        } else {
            return new Result.Success<>(eventMsg.getData());
        }
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public Object getEvent(EventType eventType) {
        return serverNotifications.remove(eventType.getType());
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}
