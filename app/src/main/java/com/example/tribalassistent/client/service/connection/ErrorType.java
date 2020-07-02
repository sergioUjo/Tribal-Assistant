package com.example.tribalassistent.client.service.connection;

public enum ErrorType {
    SYSTEM_ERROR("System/error"),
    MESSAGE_ERROR("Message/error");

    private final String type;

    ErrorType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static ErrorType fromString(String type) {
        for (ErrorType errorType : ErrorType.values()) {
            if (errorType.getType().equals(type)) {
                return errorType;
            }
        }
        return null;
    }
}
