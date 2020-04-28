package com.example.tribalassistent.data.repositories;

import android.util.Log;

import com.example.tribalassistent.data.comunication.EventType;
import com.example.tribalassistent.data.comunication.OnResultListener;
import com.example.tribalassistent.data.comunication.SocketRequest;
import com.example.tribalassistent.data.model.authentication.CharacterSelected;
import com.example.tribalassistent.data.model.authentication.CharacterSelection;
import com.example.tribalassistent.data.model.authentication.LogInUser;
import com.example.tribalassistent.data.model.authentication.Player;

public class LoginRepository {
    private static final String TAG = "LoginRepository";
    private static LoginRepository instance;
    private Player user = null;
    private OnResultListener<Player> onLogin;
    private OnResultListener<CharacterSelected> onCharacterSelected;

    // private constructor : singleton access
    private LoginRepository() {
    }

    public static LoginRepository getInstance() {
        if (instance == null) {
            instance = new LoginRepository();
        }
        return instance;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
    }

    public Player getUser() {
        return user;
    }

    public void setLoggedInUser(Player user) {
        Log.d(TAG, "LLoging in with user " + user.getName());
        this.user = user;
        SystemRepository.getInstance().systemIdentify();
    }

    public void login(String username, String password) {
        SocketRequest<LogInUser, Player> request = new SocketRequest<>();
        request.setOnResultListener(onLogin);
        request.doInBackground(new LogInUser(username, password), EventType.AUTH_LOGIN);
    }

    public void select(int id, String world_id) {
        SocketRequest<CharacterSelection, CharacterSelected> request = new SocketRequest<>();
        request.setOnResultListener(onCharacterSelected);
        request.doInBackground(new CharacterSelection(id, world_id), EventType.AUTH_SELECT_CHARACTER);
    }

    public void setOnLogin(OnResultListener<Player> onLogin) {
        this.onLogin = onLogin;
    }

    public void setOnCharacterSelected(OnResultListener<CharacterSelected> onCharacterSelected) {
        this.onCharacterSelected = onCharacterSelected;
    }
}
