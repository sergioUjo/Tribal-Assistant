package com.example.tribalassistent.client;

import com.example.tribalassistent.data.model.gamedata.GameData;

public interface GameServiceAsync {
    void getGameData(AsyncCallback<GameData> async);

}
