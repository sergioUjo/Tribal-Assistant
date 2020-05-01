package com.example.tribalassistent.data.comunication.request;


public interface OnResultListener<O> {
    void onResult(Result<O> result);
}
