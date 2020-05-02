package com.example.tribalassistent.data.comunication.request;

import com.example.tribalassistent.data.model.authentication.CharacterSelected;
import com.example.tribalassistent.data.model.authentication.Player;
import com.example.tribalassistent.data.model.authentication.Reconnect;

public class ReconnectRequest extends SocketRequest<Reconnect, Void> {

    public ReconnectRequest(Player player, CharacterSelected selected) {
        super(new Reconnect(player.getName(), player.getToken(), selected.getId(), selected.getWorld_id()), RequestType.AUTH_RECONNECT);
    }

    @Override
    Class<Void> getClazz() {
        return Void.class;
    }
}
