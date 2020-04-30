package com.example.tribalassistent.data.repositories;

import androidx.lifecycle.MutableLiveData;

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
        if (info.getValue() == null) {
            requestCharacterInfo();
        }
        return info;
    }

    private void requestCharacterInfo() {
        SocketRequest<Object, CharacterInfo> request = new SocketRequest<>(new OnResultListener<CharacterInfo>() {
            @SneakyThrows
            @Override
            public void onResult(Result<CharacterInfo> result) {
                info.postValue(result.getData());
            }
        });
        request.doInBackground(null, EventType.CHARACTER_GET_INFO);
    }

}

