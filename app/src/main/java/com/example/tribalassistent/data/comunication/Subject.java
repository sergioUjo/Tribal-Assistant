package com.example.tribalassistent.data.comunication;

public interface Subject<T> {

    void observe(Observer<T> observer);

    void notifyObservers(T event);
}
