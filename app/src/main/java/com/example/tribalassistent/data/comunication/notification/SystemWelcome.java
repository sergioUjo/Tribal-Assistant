package com.example.tribalassistent.data.comunication.notification;

import com.example.tribalassistent.data.model.system.Welcome;
import com.example.tribalassistent.data.repositories.SystemRepository;

class SystemWelcome extends Notification {
    private Welcome welcome;

    public SystemWelcome(Welcome welcome) {
        this.welcome = welcome;
    }

    @Override
    public void apply() {
        SystemRepository.getInstance().systemWelcome(welcome);
    }
}
