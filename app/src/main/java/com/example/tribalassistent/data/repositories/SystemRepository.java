package com.example.tribalassistent.data.repositories;

import android.util.Log;

import com.example.tribalassistent.data.comunication.request.ReconnectRequest;
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
        }
        return instance;
    }

    // REQUEST

    public void systemIdentify() {
        SystemIdentifyRequest request = new SystemIdentifyRequest();
        request.doInBackground();
    }

    // NOTIFICATION

    public void systemWelcome(Welcome welcome) {
        Log.d(TAG, welcome.getMessage());
        LoginRepository loginRepository = LoginRepository.getInstance();
        if (loginRepository.isLoggedIn()) {
            ReconnectRequest request = new ReconnectRequest(loginRepository.getUser(), loginRepository.getSelected());
            request.doInBackground();
        }
    }

}
