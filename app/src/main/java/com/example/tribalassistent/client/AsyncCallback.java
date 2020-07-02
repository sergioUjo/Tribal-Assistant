package com.example.tribalassistent.client;

import com.example.tribalassistent.data.model.system.Error;

public interface AsyncCallback<T> {
    void onFailure(Error error);

    void onSuccess(T result);
}
