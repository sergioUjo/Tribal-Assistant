package com.example.tribalassistent.data.comunication.notification;

import android.util.Log;

import com.example.tribalassistent.data.comunication.JsonParser;
import com.example.tribalassistent.data.model.system.Welcome;
import com.example.tribalassistent.data.model.village.LevelChange;
import com.example.tribalassistent.data.model.village.Village;

import org.json.JSONObject;

public class NotificationFactory {
    private static final String TAG = "NotificationFactory";

    public static Notification getNotification(JSONObject jsonObject) {
        String type = jsonObject.optString("type", "");
        switch (NotificationType.fromString(type)) {
            case SYSTEM_WELCOME:
                Welcome welcome = JsonParser.parseEvent(jsonObject, Welcome.class).getData();
                return new SystemWelcome(welcome);
            case BUILDING_LEVEL_CHANGED:
                LevelChange levelChange = JsonParser.parseEvent(jsonObject, LevelChange.class).getData();
                return new BuildingLevelChanged(levelChange);
            case VILLAGE_RESOURCES_CHANGED:
                Village village = JsonParser.parseEvent(jsonObject, Village.class).getData();
                return new ResourcesChanged(village);
            default:
                Log.d(TAG, type + " not mapped");
                return null;
        }

    }
}
