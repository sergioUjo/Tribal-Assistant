package com.example.tribalassistent.data.repositories;

import com.example.tribalassistent.data.comunication.EventType;
import com.example.tribalassistent.data.comunication.OnResultListener;
import com.example.tribalassistent.data.comunication.Result;
import com.example.tribalassistent.data.comunication.SocketRequest;
import com.example.tribalassistent.data.model.character.CharacterInfo;
import com.example.tribalassistent.data.model.character.Village;

import java.util.LinkedList;
import java.util.List;

import lombok.SneakyThrows;

public class CharacterRepository {
    private static CharacterRepository instance;
    private CharacterInfo info;

    private CharacterRepository() {
    }

    public static CharacterRepository getInstance() {
        if (instance == null) {
            instance = new CharacterRepository();
        }
        return instance;
    }

    public void getCharacterInfo(final OnResultListener<CharacterInfo> resultListener) {
        SocketRequest<Object, CharacterInfo> request = new SocketRequest<>();
        request.setOnResultListener(new OnResultListener<CharacterInfo>() {
            @SneakyThrows
            @Override
            public void onResult(Result<CharacterInfo> result) {
                info = result.getData();
                resultListener.onResult(result);
            }
        });
        request.doInBackground(null, EventType.CHARACTER_GET_INFO);
    }

    public List<Integer> getVillageIds() {
        List<Integer> ids = new LinkedList<>();
        for (Village village : info.getVillages()) {
            ids.add(village.getId());
        }
        return ids;
    }

    private void setInfo(Result<CharacterInfo> result) {
        try {
            info = result.getData();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}

