package com.example.tribalassistent.data.comunication.notification;

import android.util.Log;

import com.example.tribalassistent.data.comunication.JsonParser;
import com.example.tribalassistent.data.model.system.Welcome;
import com.example.tribalassistent.data.model.village.LevelChange;
import com.example.tribalassistent.data.model.village.Village;
import com.example.tribalassistent.data.repositories.SystemRepository;
import com.example.tribalassistent.data.repositories.VillageRepository;

import org.json.JSONObject;

public class Notification {
    private static final String TAG = "Notification";

    public static void received(JSONObject jsonObject) {
        String type = jsonObject.optString("type", "");
        NotificationType notificationType = NotificationType.fromString(type);
        if (notificationType == null) {
            Log.d(TAG, type + " not mapped");
            return;
        }
        switch (notificationType) {
            case SYSTEM_WELCOME:
                Welcome welcome = JsonParser.parseEvent(jsonObject, Welcome.class).getData();
                SystemRepository.getInstance().systemWelcome(welcome);
                break;
            case BUILDING_LEVEL_CHANGED:
                LevelChange levelChange = JsonParser.parseEvent(jsonObject, LevelChange.class).getData();
                VillageRepository.getInstance().levelChange(levelChange);
                break;
            case VILLAGE_RESOURCES_CHANGED:
                Village village = JsonParser.parseEvent(jsonObject, Village.class).getData();
                VillageRepository.getInstance().resourceChanged(village);
                break;
        }

    }
}
