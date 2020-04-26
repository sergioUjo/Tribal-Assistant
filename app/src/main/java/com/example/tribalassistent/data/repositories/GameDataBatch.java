package com.example.tribalassistent.data.repositories;

import com.example.tribalassistent.data.comunication.EventType;
import com.example.tribalassistent.data.comunication.MessagerSync;
import com.example.tribalassistent.data.comunication.Result;
import com.example.tribalassistent.data.model.common.Resources;
import com.example.tribalassistent.data.model.gamedata.GameData;

public class GameDataBatch {
    private static GameDataBatch instance;
    private GameData gameData;

    private GameDataBatch() {
        gameData = requestGameData();
    }

    public static GameDataBatch getInstance() {
        if (instance == null) {
            instance = new GameDataBatch();
        }
        return instance;
    }


    public Resources getResources(String building_name, int level) {
        return gameData.getBuildings().get(building_name).getIndividual_level_costs().get(level);
    }

    private GameData requestGameData() {
        Result result = MessagerSync.send(null, EventType.GET_GAME_DATA);
        if (result instanceof Result.Error) {
            System.out.println(((Result.Error) result).getMessage());
            return null;
        }
        return ((Result.Success<GameData>) result).getData();
    }
}
