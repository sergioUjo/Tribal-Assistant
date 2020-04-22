package com.example.tribalassistent.data.model.authentication;

import lombok.Data;

@Data
public class Character {
    private Integer character_id;
    private String character_name;
    private String world_id;
    private String world_name;
    private boolean maintenance;
    private boolean allow_login;
    private Integer character_owner_id;
    private String character_owner_name;
    private boolean key_required;
}
