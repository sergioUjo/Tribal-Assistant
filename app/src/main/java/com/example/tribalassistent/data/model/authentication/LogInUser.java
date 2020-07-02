package com.example.tribalassistent.data.model.authentication;

import com.example.tribalassistent.data.comunication.request.RequestType;
import com.example.tribalassistent.data.model.Requestable;

import lombok.Data;

/**
 * Data class that captures name information for logged in users retrieved from LoginRepository
 */
@Data
public class LogInUser implements Requestable<Player> {

    private String name;
    private String pass;

    public LogInUser(String username, String password) {
        this.name = username;
        this.pass = password;
    }

    @Override
    public RequestType getType() {
        return RequestType.AUTH_LOGIN;
    }

    @Override
    public Class<Player> getResponse() {
        return Player.class;
    }
}
