package com.example.tribalassistent.data.comunication;


import com.example.tribalassistent.data.comunication.request.Result;

public interface OnResultListener<O> {
    void onResult(Result<O> result);
}
