package com.example.tribalassistent.data.repositories;

import android.util.Log;

import com.example.tribalassistent.client.AsyncCallback;
import com.example.tribalassistent.client.SystemServiceAsync;
import com.example.tribalassistent.data.model.system.Identified;
import com.example.tribalassistent.data.model.system.Identify;
import com.example.tribalassistent.data.model.system.Welcome;

public class SystemRepository extends BaseRepository implements SystemServiceAsync {
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

    public void systemWelcome(Welcome welcome) {
        Log.d(TAG, welcome.getMessage());
        LoginRepository loginRepository = LoginRepository.getInstance();
        if (loginRepository.isLoggedIn()) {
            loginRepository.reconnect();
            identify(null);
        }
    }

    @Override
    public void identify(AsyncCallback<Identified> async) {
        socketConnection.send(new Identify());
    }
}
