package com.example.tribalassistent.data.comunication.request;

import com.example.tribalassistent.data.model.secondvillage.Info;

public class SecondVillageInfoRequest extends SocketRequest<Void, Info> {

    public SecondVillageInfoRequest() {
        super(null, RequestType.SECOND_VILLAGE_GET_INFO);
    }

    @Override
    Class<Info> getClazz() {
        return Info.class;
    }

}
