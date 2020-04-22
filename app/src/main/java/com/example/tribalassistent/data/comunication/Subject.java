package com.example.tribalassistent.data.comunication;

public interface Subject {

    void registerObserver(Observer observer);

    Object getEvent(EventType eventType);

    void notifyObservers();
}
