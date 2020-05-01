package com.example.tribalassistent.data.repositories;

import com.example.tribalassistent.data.comunication.request.OnResultListener;
import com.example.tribalassistent.data.comunication.request.Result;
import com.example.tribalassistent.data.model.common.Resources;
import com.example.tribalassistent.data.model.gamedata.GameData;

public class GameDataRepository {
    private static GameDataRepository instance;
    private GameData gameData;

    private GameDataRepository(final OnResultListener<GameDataRepository> resultListener) {/*
        final SocketRequest<Object, GameData> request = new SocketRequest<>(new OnResultListener<GameData>() {
            @Override
            public void onResult(Result<GameData> result) {
                setGameData(result);
                resultListener.onResult(new Result<>(instance));
            }
        });
        request.doInBackground(null, EventType.GET_GAME_DATA);
        */
    }

    public static void getInstance(OnResultListener<GameDataRepository> resultListener) {
        if (instance == null) {
            instance = new GameDataRepository(resultListener);
        } else {
            resultListener.onResult(new Result<>(instance));
        }
    }

    public Resources getResources(String building_name, int level) {
        return gameData.getBuildings().get(building_name).getIndividual_level_costs().get(level);
    }

    private void setGameData(Result<GameData> result) {
        try {
            gameData = result.getData();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

}
