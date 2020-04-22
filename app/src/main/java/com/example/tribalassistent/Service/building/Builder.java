package com.example.tribalassistent.Service.building;

import com.example.tribalassistent.data.comunication.EventType;
import com.example.tribalassistent.data.comunication.MessagerSync;
import com.example.tribalassistent.data.comunication.Result;
import com.example.tribalassistent.data.model.building.Upgrade;
import com.example.tribalassistent.data.model.common.BuildingName;
import com.example.tribalassistent.data.model.common.Resources;
import com.example.tribalassistent.data.model.village.Building;
import com.example.tribalassistent.data.repositories.GameDataBatch;
import com.example.tribalassistent.data.repositories.VillageGameBatch;

public class Builder {
    private static final VillageGameBatch VILLAGE_BATCH = VillageGameBatch.getInstance();
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
        if (VILLAGE_BATCH.getVillageData(villageId).getQueue().size() == 2) {
            return false;
        }
        Building building = VILLAGE_BATCH.getBuilding(buildingName, villageId);

        if (requiredLevel(building, villageId) &&
                enoughResources(buildingName, building.getLevel() + 1, villageId)) {

            Result result = MessagerSync.send(new Upgrade(buildingName, villageId), EventType.BUILDING_UPGRADE);

            return result instanceof Result.Success;
        }
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
