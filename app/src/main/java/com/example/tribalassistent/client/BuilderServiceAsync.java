package com.example.tribalassistent.client;

import com.example.tribalassistent.data.model.building.Upgrading;

public interface BuilderServiceAsync {
    void completeInstantly(int jobId, int villageId);

    void upgrade(String buildingName, int villageId, AsyncCallback<Upgrading> async);
}
