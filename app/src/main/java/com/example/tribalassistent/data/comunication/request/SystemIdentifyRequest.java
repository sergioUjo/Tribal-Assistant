package com.example.tribalassistent.data.comunication.request;

import com.example.tribalassistent.data.model.system.Identified;
import com.example.tribalassistent.data.model.system.Identify;

public class SystemIdentifyRequest extends SocketRequest<Identify, Identified> {

    public SystemIdentifyRequest() {
        super(new Identify(), RequestType.SYSTEM_IDENTIFY);
    }

    @Override
    Class<Identified> getClazz() {
        return Identified.class;
    }

}
