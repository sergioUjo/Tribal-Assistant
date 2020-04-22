package com.example.tribalassistent.data.repositories;

import com.example.tribalassistent.data.comunication.EventType;
import com.example.tribalassistent.data.comunication.MessagerSync;
import com.example.tribalassistent.data.comunication.Observer;
import com.example.tribalassistent.data.comunication.SocketConnection;
import com.example.tribalassistent.data.comunication.Subject;
import com.example.tribalassistent.data.model.system.Identify;

public class SystemRepository implements Observer {
    private static SystemRepository instance;

    private SystemRepository() {
        MessagerSync.getInstance().registerObserver(this);
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
        systemWelcome(observable.getEvent(EventType.SYSTEM_WELCOME));
    }

    private void systemWelcome(Object event) {
        if (event != null) {
            if (LoginRepository.getInstance().isLoggedIn()) {
                //TODO send  token
            } else {
                MessagerSync.send(new Identify(), EventType.SYSTEM_IDENTIFY);
            }
        }
    }
}
