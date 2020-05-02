package com.example.tribalassistent.data.comunication.request;

import com.example.tribalassistent.data.model.authentication.LogInUser;
import com.example.tribalassistent.data.model.authentication.Player;

public class LoginRequest extends SocketRequest<LogInUser, Player> {

    public LoginRequest(String userName, String password) {
        super(new LogInUser(userName, password), RequestType.AUTH_LOGIN);
    }

    @Override
    Class<Player> getClazz() {
        return Player.class;
    }
}
