package com.example.tribalassistent.client.service.connection.notification;

enum NotificationType {
    SYSTEM_WELCOME("System/welcome"),
    BUILDING_LEVEL_CHANGED("Building/levelChanged"),
    VILLAGE_RESOURCES_CHANGED("Village/resourcesChanged");


    private final String type;


    NotificationType(String type) {
        this.type = type;
    }

    String getType() {
        return type;
    }

    static NotificationType fromString(String type) {
        for (NotificationType b : NotificationType.values()) {
            if (b.getType().equalsIgnoreCase(type)) {
                return b;
            }
        }
        return null;
    }
}
