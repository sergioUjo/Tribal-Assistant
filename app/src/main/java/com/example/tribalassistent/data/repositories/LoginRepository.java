package com.example.tribalassistent.data.repositories;

import com.example.tribalassistent.data.comunication.EventType;
import com.example.tribalassistent.data.comunication.MessagerSync;
import com.example.tribalassistent.data.comunication.Result;
import com.example.tribalassistent.data.model.authentication.CharacterSelected;
import com.example.tribalassistent.data.model.authentication.CharacterSelection;
import com.example.tribalassistent.data.model.authentication.LogInUser;
import com.example.tribalassistent.data.model.authentication.Player;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private static volatile LoginRepository instance;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    private Player user = null;

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

    private void setLoggedInUser(Player user) {
        this.user = user;
    }

    public Result<Player> login(String username, String password) {
        Result<Player> result = MessagerSync.send(new LogInUser(username, password), EventType.AUTH_LOGIN);
        if (result instanceof Result.Success) {
            setLoggedInUser(((Result.Success<Player>) result).getData());
            SystemRepository.getInstance().systemIdentify();
        }
        return result;
    }

    public Result<CharacterSelected> select(int id, String world_id) {
        Result<CharacterSelected> result = MessagerSync.send(new CharacterSelection(id, world_id), EventType.AUTH_SELECT_CHARACTER);
        return result;
    }

}
