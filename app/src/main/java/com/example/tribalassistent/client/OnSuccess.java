package com.example.tribalassistent.client;

import com.example.tribalassistent.data.model.system.Error;

public abstract class OnSuccess<T> implements AsyncCallback<T> {
    @Override
    public void onFailure(Error error) {
        System.out.println(error.getMessage());//TODO
    }
}
