package com.example.tribalassistent.data.comunication.request;

import com.example.tribalassistent.data.comunication.EventMsg;
import com.example.tribalassistent.data.comunication.EventMsgFactory;
import com.example.tribalassistent.data.model.authentication.CharacterSelected;
import com.example.tribalassistent.data.model.authentication.CharacterSelection;

public class CharacterSelectRequest extends SocketRequest<CharacterSelection, CharacterSelected> {
    private EventMsg<CharacterSelection> request;

    public CharacterSelectRequest(int id, String world_id) {
        request = EventMsgFactory.getEvent(new CharacterSelection(id, world_id), RequestType.AUTH_SELECT_CHARACTER);
    }

    @Override
    Class<CharacterSelected> getClazz() {
        return CharacterSelected.class;
    }

    @Override
    EventMsg<CharacterSelection> getRequest() {
        return request;
    }
}
