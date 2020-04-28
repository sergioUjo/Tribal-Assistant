package com.example.tribalassistent.data.repositories;

import android.util.Log;

import com.example.tribalassistent.data.comunication.EventType;
import com.example.tribalassistent.data.comunication.Observer;
import com.example.tribalassistent.data.comunication.OnResultListener;
import com.example.tribalassistent.data.comunication.Result;
import com.example.tribalassistent.data.comunication.SocketConnection;
import com.example.tribalassistent.data.comunication.SocketNotification;
import com.example.tribalassistent.data.comunication.SocketRequest;
import com.example.tribalassistent.data.comunication.Subject;
import com.example.tribalassistent.data.model.system.Identified;
import com.example.tribalassistent.data.model.system.Identify;

public class SystemRepository implements Observer {
    private static final String TAG = "SystemRepository";
    private static SystemRepository instance;

    private SystemRepository() {
        SocketNotification.getInstance().registerObserver(this);
    }

    public static SystemRepository getInstance() {
        if (instance == null) {
            instance = new SystemRepository();
            SocketConnection.init();
        }
        return instance;
    }

    @Override
    public void update(Subject observable) {
        Log.d(TAG, "Updating... ");
        //systemWelcome(observable.getEvent(EventType.SYSTEM_WELCOME));
    }

    public void systemIdentify() {
        SocketRequest<Identify, Identified> request = new SocketRequest<>();
        request.setOnResultListener(new OnResultListener<Identified>() {
            @Override
            public void onResult(Result<Identified> result) {

            }
        });
        request.doInBackground(new Identify(), EventType.SYSTEM_IDENTIFY);
    }
}
