package com.example.tribalassistent.client;

import android.util.Log;

import com.example.tribalassistent.data.model.system.Error;

public abstract class OnSuccess<T> implements AsyncCallback<T> {
    @Override
    public void onFailure(Error error) {
        Log.d("DefaultHandler", error.getMessage());
    }
}
