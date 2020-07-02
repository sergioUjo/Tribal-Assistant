package com.example.tribalassistent.client;

import com.example.tribalassistent.data.model.authentication.CharacterSelected;
import com.example.tribalassistent.data.model.authentication.Player;

public interface AuthServiceAsync {
    void login(String username, String password, AsyncCallback<Player> async);

    void completeLogin();

    void selectCharacter(int id, String worldId, AsyncCallback<CharacterSelected> async);

    void reconnect(Player player, CharacterSelected selected);
}
