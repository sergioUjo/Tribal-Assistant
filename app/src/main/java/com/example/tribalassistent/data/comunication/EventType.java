package com.example.tribalassistent.data.comunication;

import com.example.tribalassistent.data.model.authentication.CharacterSelected;
import com.example.tribalassistent.data.model.authentication.CharacterSelection;
import com.example.tribalassistent.data.model.authentication.LogInUser;
import com.example.tribalassistent.data.model.authentication.Player;
import com.example.tribalassistent.data.model.building.Upgrade;
import com.example.tribalassistent.data.model.building.Upgrading;
import com.example.tribalassistent.data.model.character.CharacterInfo;
import com.example.tribalassistent.data.model.gamedata.GameData;
import com.example.tribalassistent.data.model.system.Error;
import com.example.tribalassistent.data.model.system.Identified;
import com.example.tribalassistent.data.model.system.Identify;
import com.example.tribalassistent.data.model.system.Welcome;
import com.example.tribalassistent.data.model.village.Village;
import com.example.tribalassistent.data.model.village.VillageGameBatch;
import com.example.tribalassistent.data.model.village.VillageIds;

public enum EventType {
    AUTH_LOGIN("Authentication/login", LogInUser.class),
    AUTH_SELECT_CHARACTER("Authentication/selectCharacter", CharacterSelection.class),
    AUTH_CHARACTER_SELECTED("Authentication/characterSelected", CharacterSelected.class),
    SYSTEM_WELCOME("System/welcome", Welcome.class),
    SYSTEM_IDENTIFY("System/identify", Identify.class),
    SYSTEM_IDENTIFIED("System/identified", Identified.class),
    SYSTEM_ERROR("System/error", Error.class),
    LOGIN_SUCCESS("Login/success", Player.class),
    MESSAGE_ERROR("Message/error", Error.class),
    GET_GAME_DATA("GameDataBatch/getGameData", null),
    GAME_DATA("GameDataBatch/gameData", GameData.class),
    GET_VILLAGE_DATA("VillageBatch/getVillageData", VillageIds.class),
    VILLAGE_DATA("VillageBatch/villageData", VillageGameBatch.class),
    CHARACTER_INFO("Character/Info", CharacterInfo.class),
    BUILDING_UPGRADE("Building/upgrade", Upgrade.class),
    BUILDING_UPGRADING("Building/upgrading", Upgrading.class),
    VILLAGE_RESOURCE_CHANGED("Village/resourcesChanged", Village.class),
    CHARACTER_GET_INFO("Character/getInfo", null);


    private final String type;
    private final Class clazz;

    private EventType(String type, Class clazz) {
        this.type = type;
        this.clazz = clazz;
    }

    public String getType() {
        return type;
    }

    public Class getClazz() {
        return clazz;
    }

    public static EventType fromString(String type) {
        for (EventType b : EventType.values()) {
            if (b.getType().equalsIgnoreCase(type)) {
                return b;
            }
        }
        return null;
    }
}
