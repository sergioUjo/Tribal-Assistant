package com.example.tribalassistent.client;

import com.example.tribalassistent.data.model.character.CharacterInfo;

public interface CharacterServiceAsync {
    void getInfo(AsyncCallback<CharacterInfo> async);
}
