package com.example.tribalassistent.data.repositories;

import com.example.tribalassistent.data.comunication.request.SecondVillageInfoRequest;
import com.example.tribalassistent.data.model.secondvillage.Info;

public class SecondVillageRepository {
    private static SecondVillageRepository instance;
    private Info villageInfo;

    private SecondVillageRepository() {
    }

    public static SecondVillageRepository getInstance() {
        if (instance == null) {
            instance = new SecondVillageRepository();
        }
        return instance;
    }

    public void requestInfo() {
        SecondVillageInfoRequest request = new SecondVillageInfoRequest();
        request.onResultListener(result -> {
            try {
                villageInfo = result.getData();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        });
        request.doInBackground();
    }
}
