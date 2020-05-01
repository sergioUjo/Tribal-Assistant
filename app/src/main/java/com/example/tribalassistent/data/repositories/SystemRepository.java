package com.example.tribalassistent.data.repositories;

import com.example.tribalassistent.data.comunication.SocketConnection;
import com.example.tribalassistent.data.comunication.request.SystemIdentifyRequest;
import com.example.tribalassistent.data.model.system.Welcome;

public class SystemRepository {
    private static final String TAG = "SystemRepository";
    private static SystemRepository instance;

    private SystemRepository() {
    }

    public static SystemRepository getInstance() {
        if (instance == null) {
            instance = new SystemRepository();
            SocketConnection.init();
        }
        return instance;
    }


    public void systemWelcome(Welcome welcome) {

    }

    public void systemIdentify() {
        SystemIdentifyRequest request = new SystemIdentifyRequest();
        request.doInBackground();
    }
}
