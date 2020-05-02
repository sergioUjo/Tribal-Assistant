package com.example.tribalassistent.data.comunication.request;

import com.example.tribalassistent.data.model.building.Upgrade;
import com.example.tribalassistent.data.model.building.Upgrading;

public class UpgradeRequest extends SocketRequest<Upgrade, Upgrading> {

    public UpgradeRequest(String buildingName, int villageId) {
        super(new Upgrade(buildingName, villageId), RequestType.BUILDING_UPGRADE);
    }

    @Override
    Class<Upgrading> getClazz() {
        return Upgrading.class;
    }
}
