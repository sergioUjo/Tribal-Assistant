package com.example.tribalassistent.data.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.tribalassistent.data.comunication.request.CharacterInfoRequest;
import com.example.tribalassistent.data.model.character.CharacterInfo;
import com.example.tribalassistent.data.model.character.Village;

import java.util.LinkedList;
import java.util.List;

public class CharacterRepository {
    private static CharacterRepository instance;
    private MutableLiveData<CharacterInfo> info;

    private CharacterRepository() {
        info = new MutableLiveData<>();
    }

    public static CharacterRepository getInstance() {
        if (instance == null) {
            instance = new CharacterRepository();
        }
        return instance;
    }

    public List<Integer> getVillageIds() {
        List<Integer> ids = new LinkedList<>();
        for (Village village : info.getValue().getVillages()) {
            ids.add(village.getId());
        }
        return ids;
    }

    public MutableLiveData<CharacterInfo> getCharacterInfo() {
        return info;
    }

    public void requestCharacterInfo() {
        CharacterInfoRequest infoRequest = new CharacterInfoRequest();
        infoRequest.onResultListener(result -> {
            try {
                info.postValue(result.getData());
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        });
        infoRequest.doInBackground();
    }

}

