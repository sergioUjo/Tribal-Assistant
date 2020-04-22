package com.example.tribalassistent.data.model.authentication;

import java.util.List;

import lombok.Data;

@Data
public class CharacterSelected {
    private int id;
    private String world_id;
    private String map_name;
    private String name;
    private int owner_id;
    private String owner_name;
    private int tribe_id;
    private List<String> tribe_rights;
}

