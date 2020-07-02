package com.example.tribalassistent.data.model.authentication;

import com.example.tribalassistent.data.comunication.request.RequestType;
import com.example.tribalassistent.data.model.Requestable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CharacterSelection implements Requestable<CharacterSelected> {
    private int id;
    private String world_id;

    @Override
    public RequestType getType() {
        return RequestType.AUTH_SELECT_CHARACTER;
    }

    @Override
    public Class<CharacterSelected> getResponse() {
        return CharacterSelected.class;
    }
}
