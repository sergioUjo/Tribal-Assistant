package com.example.tribalassistent.data.repositories;

public interface Observer<T> {
    void update(T event);
}
