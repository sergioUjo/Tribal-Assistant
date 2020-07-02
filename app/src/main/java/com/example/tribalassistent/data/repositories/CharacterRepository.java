package com.example.tribalassistent.data.repositories;

import com.example.tribalassistent.client.AsyncCallback;
import com.example.tribalassistent.client.CharacterServiceAsync;
import com.example.tribalassistent.client.OnSuccess;
import com.example.tribalassistent.data.comunication.request.RequestType;
import com.example.tribalassistent.data.model.Requestable;
import com.example.tribalassistent.data.model.character.CharacterInfo;
import com.example.tribalassistent.data.model.character.Village;

import java.util.LinkedList;
import java.util.List;

public class CharacterRepository extends BaseRepository implements CharacterServiceAsync {
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

    @Override
    public void getInfo(AsyncCallback<CharacterInfo> async) {
        OnSuccess<CharacterInfo> onSuccess = new OnSuccess<CharacterInfo>() {
            @Override
            public void onSuccess(CharacterInfo result) {
                info = result;
                async.onSuccess(result);
            }
        };
        socketConnection.send(new Requestable<CharacterInfo>() {
            @Override
            public RequestType getType() {
                return RequestType.CHARACTER_GET_INFO;
            }

            @Override
            public Class<CharacterInfo> getResponse() {
                return CharacterInfo.class;
            }
        }, onSuccess);
    }
}

