package com.example.tribalassistent.client;

import com.example.tribalassistent.data.model.secondvillage.Info;
import com.example.tribalassistent.data.model.secondvillage.JobStarted;

public interface SecondVillageServiceAsync {
    void getInfo(AsyncCallback<Info> async);

    void startJob(String jobId, int villageId, AsyncCallback<JobStarted> async);

}
