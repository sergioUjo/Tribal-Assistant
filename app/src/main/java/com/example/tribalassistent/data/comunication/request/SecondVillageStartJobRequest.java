package com.example.tribalassistent.data.comunication.request;

import com.example.tribalassistent.data.model.secondvillage.JobStarted;
import com.example.tribalassistent.data.model.secondvillage.StartJob;

public class SecondVillageStartJobRequest extends SocketRequest<StartJob, JobStarted> {

    public SecondVillageStartJobRequest(String job_id, int village_id) {
        super(new StartJob(job_id, village_id), RequestType.SECOND_VILLAGE_START_JOB);
    }

    @Override
    Class<JobStarted> getClazz() {
        return JobStarted.class;
    }

}
