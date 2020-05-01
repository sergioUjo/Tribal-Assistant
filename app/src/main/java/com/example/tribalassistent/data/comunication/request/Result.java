package com.example.tribalassistent.data.comunication.request;

import com.example.tribalassistent.data.model.system.Error;

/**
 * A generic class that holds a result success w/ data or an error exception.
 */
public class Result<T> {
    private String errorMessage;
    private T data;

    Result(Error error) {
        this.errorMessage = error.getMessage();
    }

    public Result(T data) {
        this.data = data;
    }


    public T getData() throws NoSuchFieldException {
        if (errorMessage != null) {
            throw new NoSuchFieldException(errorMessage);
        }
        return data;
    }
}
