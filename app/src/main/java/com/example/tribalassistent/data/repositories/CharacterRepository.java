package com.example.tribalassistent.data.repositories;

import com.example.tribalassistent.data.comunication.EventType;
import com.example.tribalassistent.data.comunication.MessagerSync;
import com.example.tribalassistent.data.comunication.Result;
import com.example.tribalassistent.data.model.character.CharacterInfo;
import com.example.tribalassistent.data.model.character.Village;

import java.util.LinkedList;
import java.util.List;

public class CharacterRepository {
    private static CharacterRepository instance;
    private CharacterInfo info;

    private CharacterRepository() {
        info = requestInfo();
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

    private CharacterInfo requestInfo() {
        Result result = MessagerSync.send(null, EventType.CHARACTER_GET_INFO);
        if (result instanceof Result.Error) {
            System.out.println(((Result.Error) result).getMessage());
            return null;
        }
        return ((Result.Success<CharacterInfo>) result).getData();
    }
}
