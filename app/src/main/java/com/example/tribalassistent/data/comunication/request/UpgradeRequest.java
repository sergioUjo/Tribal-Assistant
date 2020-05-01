package com.example.tribalassistent.data.comunication.request;

import com.example.tribalassistent.data.comunication.EventMsg;
import com.example.tribalassistent.data.comunication.EventMsgFactory;
import com.example.tribalassistent.data.comunication.EventType;
import com.example.tribalassistent.data.model.building.Upgrade;
import com.example.tribalassistent.data.model.building.Upgrading;

public class UpgradeRequest extends SocketRequest<Upgrade, Upgrading> {
    private EventMsg<Upgrade> request;

    public UpgradeRequest(String buildingName, int villageId) {
        request = EventMsgFactory.getEvent(new Upgrade(buildingName, villageId), EventType.BUILDING_UPGRADE);
    }

    @Override
    Class<Upgrading> getClazz() {
        return Upgrading.class;
    }

    @Override
    EventMsg<Upgrade> getRequest() {
        return request;
    }
}
