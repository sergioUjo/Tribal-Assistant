package com.example.tribalassistent.data.model.authentication;

import lombok.Data;

/**
 * Data class that captures name information for logged in users retrieved from LoginRepository
 */
@Data
public class LogInUser {

    private String name;
    private String pass;

    public LogInUser(String username, String password) {
        this.name = username;
        this.pass = password;
    }
}
