package com.example.tribalassistent.data.repositories;

import android.util.Log;

import com.example.tribalassistent.data.comunication.EventMsg;
import com.example.tribalassistent.data.comunication.SocketConnection;
import com.example.tribalassistent.data.comunication.notification.SocketNotification;
import com.example.tribalassistent.data.comunication.request.SystemIdentifyRequest;

public class SystemRepository implements Observer<EventMsg> {
    private static final String TAG = "SystemRepository";
    private static SystemRepository instance;

    private SystemRepository() {
        SocketNotification.getInstance().observe(this);
    }

    public static SystemRepository getInstance() {
        if (instance == null) {
            instance = new SystemRepository();
            SocketConnection.init();
        }
        return instance;
    }

    @Override
    public void update(EventMsg eventMsg) {
        Log.d(TAG, "Updating... ");
        //systemWelcome(observable.getEvent(EventType.SYSTEM_WELCOME));
    }

    public void systemIdentify() {
        SystemIdentifyRequest request = new SystemIdentifyRequest();
        request.doInBackground();
    }
}
