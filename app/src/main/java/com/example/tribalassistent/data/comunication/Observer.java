package com.example.tribalassistent.data.comunication;

public interface Observer<T> {
    void update(T event);
}
