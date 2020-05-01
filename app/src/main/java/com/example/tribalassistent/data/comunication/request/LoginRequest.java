package com.example.tribalassistent.data.comunication.request;

import com.example.tribalassistent.data.comunication.EventMsg;
import com.example.tribalassistent.data.comunication.EventMsgFactory;
import com.example.tribalassistent.data.model.authentication.LogInUser;
import com.example.tribalassistent.data.model.authentication.Player;

public class LoginRequest extends SocketRequest<LogInUser, Player> {
    private EventMsg<LogInUser> request;

    public LoginRequest(String userName, String password) {
        request = EventMsgFactory.getEvent(new LogInUser(userName, password), RequestType.AUTH_LOGIN);
    }

    @Override
    Class<Player> getClazz() {
        return Player.class;
    }

    @Override
    EventMsg<LogInUser> getRequest() {
        return request;
    }
}
