package com.example.tribalassistent.data.repositories;

public interface Subject<T> {

    void observe(Observer<T> observer);

    void notifyObservers();
}
