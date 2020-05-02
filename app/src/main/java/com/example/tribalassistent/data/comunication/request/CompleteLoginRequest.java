package com.example.tribalassistent.data.comunication.request;

public class CompleteLoginRequest extends SocketRequest<Void, Void> {

    public CompleteLoginRequest() {
        super(null, RequestType.AUTH_COMPLETE_LOGIN);
    }

    @Override
    Class<Void> getClazz() {
        return Void.class;
    }
}
