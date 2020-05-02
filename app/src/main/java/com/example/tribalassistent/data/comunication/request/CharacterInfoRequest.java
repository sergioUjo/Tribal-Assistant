package com.example.tribalassistent.data.comunication.request;

import com.example.tribalassistent.data.model.character.CharacterInfo;

public class CharacterInfoRequest extends SocketRequest<Void, CharacterInfo> {

    public CharacterInfoRequest() {
        super(null, RequestType.CHARACTER_GET_INFO);
    }

    @Override
    Class<CharacterInfo> getClazz() {
        return CharacterInfo.class;
    }
}
