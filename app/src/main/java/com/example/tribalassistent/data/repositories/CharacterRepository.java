package com.example.tribalassistent.data.repositories;

import com.example.tribalassistent.data.comunication.request.CharacterInfoRequest;
import com.example.tribalassistent.data.comunication.request.OnResultListener;
import com.example.tribalassistent.data.model.character.CharacterInfo;
import com.example.tribalassistent.data.model.character.Village;

import java.util.LinkedList;
import java.util.List;

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

    public List<Integer> getVillageIds() {
        List<Integer> ids = new LinkedList<>();
        for (Village village : info.getVillages()) {
            ids.add(village.getId());
        }
        return ids;
    }

    public void requestCharacterInfo(OnResultListener<CharacterInfo> onResultListener) {
        CharacterInfoRequest infoRequest = new CharacterInfoRequest();
        infoRequest.onResultListener(result -> {
            try {
                info = result.getData();
                onResultListener.onResult(info);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        });
        infoRequest.doInBackground();
    }

}

