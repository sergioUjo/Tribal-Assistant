package com.example.tribalassistent.data.comunication.request;

import com.example.tribalassistent.data.model.village.VillageGameBatch;
import com.example.tribalassistent.data.model.village.VillageIds;

import java.util.List;

public class VillageGameBatchRequest extends SocketRequest<VillageIds, VillageGameBatch> {

    public VillageGameBatchRequest(List<Integer> villageIds) {
        super(new VillageIds(villageIds), RequestType.GET_VILLAGE_DATA);
    }

    @Override
    Class<VillageGameBatch> getClazz() {
        return VillageGameBatch.class;
    }

}
