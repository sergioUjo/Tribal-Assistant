package com.example.tribalassistent.Service.building;

import android.util.Log;

import com.example.tribalassistent.data.comunication.EventType;
import com.example.tribalassistent.data.comunication.MessagerSync;
import com.example.tribalassistent.data.comunication.Result;
import com.example.tribalassistent.data.model.building.Upgrade;
import com.example.tribalassistent.data.model.common.BuildingName;
import com.example.tribalassistent.data.model.common.Resources;
import com.example.tribalassistent.data.model.village.Building;
import com.example.tribalassistent.data.repositories.GameDataBatch;
import com.example.tribalassistent.data.repositories.VillageRepository;

public class Builder {
    private static final String TAG = "Builder";
    private static final VillageRepository VILLAGE_BATCH = VillageRepository.getInstance();
    private static final GameDataBatch GAME_BATCH = GameDataBatch.getInstance();
    private static Builder instance;

    private Builder() {
    }

    public static Builder getInstance() {
        if (instance == null) {
            instance = new Builder();
        }
        return instance;
    }

    public boolean build(String buildingName, int villageId) {
        Log.d(TAG, "Received request from " + villageId + " to build " + buildingName);

        if (VILLAGE_BATCH.getVillageData(villageId).getQueue().getUnlocked_slots() == 0) {
            Log.d(TAG, "No building slots available");
            return false;
        }
        Building building = VILLAGE_BATCH.getBuilding(buildingName, villageId);

        if (requiredLevel(building, villageId) &&
                enoughResources(buildingName, building.getLevel() + 1, villageId)) {

            Result result = MessagerSync.send(new Upgrade(buildingName, villageId), EventType.BUILDING_UPGRADE);

            return result instanceof Result.Success;
        }
        Log.d(TAG, "Not enough resources or doesn't have required level");
        return false;
    }

    private boolean requiredLevel(Building building, int villageId) {

        int headQuarterLevel = VILLAGE_BATCH.getBuilding(BuildingName.HEAD_QUARTER.getName(), villageId).getLevel();

        return headQuarterLevel >= building.getRequired_level();
    }

    private boolean enoughResources(String name, int futureLevel, int villageId) {
        Resources needed = GAME_BATCH.getResources(name, futureLevel);

        if (needed == null) return false;

        Resources current = VILLAGE_BATCH.getResources(villageId);

        return needed.getFood() <= current.getFood() &&
                needed.getClay() <= current.getClay() &&
                needed.getIron() <= current.getIron() &&
                needed.getWood() <= current.getWood();
    }
}
