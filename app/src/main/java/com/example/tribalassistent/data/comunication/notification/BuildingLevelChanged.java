package com.example.tribalassistent.data.comunication.notification;

import com.example.tribalassistent.data.model.village.LevelChange;
import com.example.tribalassistent.data.repositories.VillageRepository;

class BuildingLevelChanged extends Notification {
    private LevelChange levelChange;

    public BuildingLevelChanged(LevelChange levelChange) {
        this.levelChange = levelChange;
    }

    @Override
    public void apply() {
        VillageRepository.getInstance().levelChange(levelChange);
    }
}
