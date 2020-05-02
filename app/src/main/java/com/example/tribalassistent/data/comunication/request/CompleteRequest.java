package com.example.tribalassistent.data.comunication.request;

import com.example.tribalassistent.data.model.building.Complete;

public class CompleteRequest extends SocketRequest<Complete, Void> {

    public CompleteRequest(int job_id, int village_id) {
        super(new Complete(job_id, "slot", 0, village_id), RequestType.BUILDING_COMPLETE);
    }

    @Override
    Class<Void> getClazz() {
        return Void.class;
    }
}
