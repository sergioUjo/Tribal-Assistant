package com.example.tribalassistent.data.comunication.request;

import com.example.tribalassistent.data.comunication.EventMsg;
import com.example.tribalassistent.data.comunication.EventMsgFactory;
import com.example.tribalassistent.data.comunication.EventType;
import com.example.tribalassistent.data.model.village.VillageGameBatch;
import com.example.tribalassistent.data.model.village.VillageIds;

import java.util.List;

public class VillageGameBatchRequest extends SocketRequest<VillageIds, VillageGameBatch> {
    private EventMsg<VillageIds> request;

    public VillageGameBatchRequest(List<Integer> villageIds) {
        request = EventMsgFactory.getEvent(new VillageIds(villageIds), EventType.GET_VILLAGE_DATA);
    }

    @Override
    Class<VillageGameBatch> getClazz() {
        return VillageGameBatch.class;
    }

    @Override
    EventMsg<VillageIds> getRequest() {
        return request;
    }
}
