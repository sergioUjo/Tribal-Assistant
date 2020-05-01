package com.example.tribalassistent.data.comunication.request;

import com.example.tribalassistent.data.comunication.EventMsg;
import com.example.tribalassistent.data.comunication.EventMsgFactory;
import com.example.tribalassistent.data.comunication.EventType;
import com.example.tribalassistent.data.model.system.Identified;
import com.example.tribalassistent.data.model.system.Identify;

public class SystemIdentifyRequest extends SocketRequest<Identify, Identified> {
    private EventMsg<Identify> request;

    public SystemIdentifyRequest() {
        request = EventMsgFactory.getEvent(new Identify(), EventType.SYSTEM_IDENTIFY);
    }

    @Override
    Class<Identified> getClazz() {
        return Identified.class;
    }

    @Override
    EventMsg<Identify> getRequest() {
        return request;
    }
}
