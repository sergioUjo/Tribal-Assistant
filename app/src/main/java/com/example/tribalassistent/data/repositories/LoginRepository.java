package com.example.tribalassistent.data.repositories;

import com.example.tribalassistent.data.comunication.request.CharacterSelectRequest;
import com.example.tribalassistent.data.comunication.request.CompleteLoginRequest;
import com.example.tribalassistent.data.comunication.request.LoginRequest;
import com.example.tribalassistent.data.comunication.request.OnResultListener;
import com.example.tribalassistent.data.comunication.request.Result;
import com.example.tribalassistent.data.model.authentication.CharacterSelected;
import com.example.tribalassistent.data.model.authentication.Player;

public class LoginRepository {
    private static final String TAG = "LoginRepository";
    private static LoginRepository instance;
    private Player user = null;
    private CharacterSelected selected;

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

    public CharacterSelected getSelected() {
        return selected;
    }

    public void login(String username, String password, OnResultListener<Result<Player>> onResultListener) {
        LoginRequest loginRequest = new LoginRequest(username, password);
        loginRequest.onResultListener(result -> {
            onResultListener.onResult(result);
            try {
                user = result.getData();
            } catch (NoSuchFieldException ignored) {
            }
        });
        loginRequest.doInBackground();
    }

    public void select(int id, String world_id, OnResultListener<CharacterSelected> onResultListener) {
        CharacterSelectRequest selectRequest = new CharacterSelectRequest(id, world_id);
        selectRequest.onResultListener(result -> {
            try {
                selected = result.getData();
                onResultListener.onResult(selected);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        });
        selectRequest.doInBackground();
    }

    public void completeLogin() {
        CompleteLoginRequest request = new CompleteLoginRequest();
        request.doInBackground();
    }
}
