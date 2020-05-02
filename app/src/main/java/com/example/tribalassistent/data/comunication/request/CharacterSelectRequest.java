package com.example.tribalassistent.data.comunication.request;

import com.example.tribalassistent.data.model.authentication.CharacterSelected;
import com.example.tribalassistent.data.model.authentication.CharacterSelection;

public class CharacterSelectRequest extends SocketRequest<CharacterSelection, CharacterSelected> {

    public CharacterSelectRequest(int id, String world_id) {
        super(new CharacterSelection(id, world_id), RequestType.AUTH_SELECT_CHARACTER);
    }

    @Override
    Class<CharacterSelected> getClazz() {
        return CharacterSelected.class;
    }
}
