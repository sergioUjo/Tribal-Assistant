package com.example.tribalassistent.data.comunication.request;

public enum RequestType {
    AUTH_LOGIN("Authentication/login"),
    AUTH_COMPLETE_LOGIN("Authentication/completeLogin"),
    AUTH_SELECT_CHARACTER("Authentication/selectCharacter"),
    AUTH_RECONNECT("Authentication/reconnect"),
    SYSTEM_IDENTIFY("System/identify"),
    GET_GAME_DATA("GameDataBatch/getGameData"),
    GET_VILLAGE_DATA("VillageBatch/getVillageData"),
    BUILDING_UPGRADE("Building/upgrade"),
    CHARACTER_GET_INFO("Character/getInfo"),
    BUILDING_COMPLETE("Building/completeInstantly"),
    SECOND_VILLAGE_GET_INFO("SecondVillage/getInfo"),
    SECOND_VILLAGE_START_JOB("SecondVillage/startJob");


    private final String type;

    RequestType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

    public static RequestType fromString(String type) {
        for (RequestType b : RequestType.values()) {
            if (b.toString().equalsIgnoreCase(type)) {
                return b;
            }
        }
        return null;
    }
}
