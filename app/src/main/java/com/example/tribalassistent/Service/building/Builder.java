package com.example.tribalassistent.Service.building;

import com.example.tribalassistent.data.comunication.EventType;
import com.example.tribalassistent.data.comunication.OnResultListener;
import com.example.tribalassistent.data.comunication.SocketRequest;
import com.example.tribalassistent.data.model.building.Upgrade;
import com.example.tribalassistent.data.model.building.Upgrading;

public class Builder {
    private static final String TAG = "Builder";

    public void setOnEventListener(OnResultListener<Upgrading> onEventListener) {
        this.onEventListener = onEventListener;
    }

    private OnResultListener<Upgrading> onEventListener;
    //private static final VillageRepository VILLAGE_BATCH = VillageRepository.getInstance();
    //private static final GameDataBatch GAME_BATCH = GameDataBatch.getInstance();

    public void build(String buildingName, int villageId) {
        SocketRequest<Upgrade, Upgrading> request = new SocketRequest<>();
        request.setOnResultListener(onEventListener);
        request.doInBackground(new Upgrade(buildingName, villageId), EventType.BUILDING_UPGRADE);
    }

}
