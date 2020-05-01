package com.example.tribalassistent.Service.building;

import com.example.tribalassistent.data.comunication.OnResultListener;
import com.example.tribalassistent.data.comunication.request.UpgradeRequest;
import com.example.tribalassistent.data.model.building.Upgrading;

public class Builder {
    private static final String TAG = "Builder";
    private static Builder instance;
    private OnResultListener<Upgrading> onEventListener;

    public static Builder getInstance() {
        if (instance == null) {
            instance = new Builder();
        }
        return instance;
    }

    public Builder() {
        this.onEventListener = Manager.getInstance();
    }

    //private static final VillageRepository VILLAGE_BATCH = VillageRepository.getInstance();
    //private static final GameDataBatch GAME_BATCH = GameDataBatch.getInstance();

    public void build(String buildingName, int villageId) {
        UpgradeRequest request = new UpgradeRequest(buildingName, villageId);
        request.onResultListener(onEventListener);
        request.doInBackground();
    }

}
