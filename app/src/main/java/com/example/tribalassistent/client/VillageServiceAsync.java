package com.example.tribalassistent.client;

import com.example.tribalassistent.data.model.village.VillageGameBatch;

import java.util.List;

public interface VillageServiceAsync {
    void getVillageData(List<Integer> villageIds, AsyncCallback<VillageGameBatch> async);
}
