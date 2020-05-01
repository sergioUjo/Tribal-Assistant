package com.example.tribalassistent.data.comunication.request;

import com.example.tribalassistent.data.comunication.EventMsg;
import com.example.tribalassistent.data.comunication.EventMsgFactory;
import com.example.tribalassistent.data.model.character.CharacterInfo;

public class CharacterInfoRequest extends SocketRequest<Void, CharacterInfo> {
    private EventMsg<Void> request;

    public CharacterInfoRequest() {
        request = EventMsgFactory.getEvent(null, RequestType.CHARACTER_GET_INFO);
    }

    @Override
    Class<CharacterInfo> getClazz() {
        return CharacterInfo.class;
    }

    @Override
    EventMsg<Void> getRequest() {
        return request;
    }
}
