package com.example.tribalassistent.data.comunication.notification;

import com.example.tribalassistent.data.model.village.Village;
import com.example.tribalassistent.data.repositories.VillageRepository;

class ResourcesChanged extends Notification {
    private Village village;

    public ResourcesChanged(Village village) {
        this.village = village;
    }

    @Override
    public void apply() {
        VillageRepository.getInstance().resourceChanged(village);
    }
}
