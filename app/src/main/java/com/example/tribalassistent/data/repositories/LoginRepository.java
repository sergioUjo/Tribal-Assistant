package com.example.tribalassistent.data.repositories;

import com.example.tribalassistent.client.AsyncCallback;
import com.example.tribalassistent.client.AuthServiceAsync;
import com.example.tribalassistent.client.OnSuccess;
import com.example.tribalassistent.client.service.connection.RequestType;
import com.example.tribalassistent.data.model.Requestable;
import com.example.tribalassistent.data.model.authentication.CharacterSelected;
import com.example.tribalassistent.data.model.authentication.CharacterSelection;
import com.example.tribalassistent.data.model.authentication.LogInUser;
import com.example.tribalassistent.data.model.authentication.Player;
import com.example.tribalassistent.data.model.authentication.Reconnect;
import com.example.tribalassistent.data.model.system.Error;

public class LoginRepository extends BaseRepository implements AuthServiceAsync {
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

    @Override
    public void login(String username, String password, AsyncCallback<Player> async) {
        AsyncCallback<Player> callback = new AsyncCallback<Player>() {
            @Override
            public void onFailure(Error error) {
                async.onFailure(error);
            }

            @Override
            public void onSuccess(Player result) {
                user = result;
                async.onSuccess(result);
            }
        };
        socketConnection.send(new LogInUser(username, password), callback);

    }

    @Override
    public void completeLogin() {
        socketConnection.send(new Requestable<Void>() {
            @Override
            public RequestType getType() {
                return RequestType.AUTH_COMPLETE_LOGIN;
            }

            @Override
            public Class<Void> getResponse() {
                return Void.class;
            }
        });
    }

    @Override
    public void selectCharacter(int id, String worldId, AsyncCallback<CharacterSelected> async) {
        OnSuccess<CharacterSelected> callback = new OnSuccess<CharacterSelected>() {
            @Override
            public void onSuccess(CharacterSelected result) {
                selected = result;
                async.onSuccess(result);
            }
        };
        socketConnection.send(new CharacterSelection(id, worldId), callback);
    }

    public void reconnect() {
        reconnect(user, selected);
    }

    @Override
    public void reconnect(Player player, CharacterSelected selected) {
        socketConnection.send(new Reconnect(player.getName(), player.getToken(), player.getPlayer_id(), selected.getWorld_id()));
    }
}
