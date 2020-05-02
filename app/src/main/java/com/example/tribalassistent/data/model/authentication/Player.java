package com.example.tribalassistent.data.model.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import lombok.Data;

@Data
public class Player {
    private int player_id;
    private String name;
    private String token;
    private List<Character> characters;
    private List<World> worlds;
    private Object[] invitations;
    private int premium;
    private long server_timestamp;
    private boolean first_login;
    @JsonProperty("is_guest")
    private boolean is_guest;
    private boolean vip;
    private boolean accepted_adjust;
    private boolean accepted_pixels;
    private boolean newsletter_window;
}
