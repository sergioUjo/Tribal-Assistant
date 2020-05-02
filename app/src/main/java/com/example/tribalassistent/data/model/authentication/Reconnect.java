package com.example.tribalassistent.data.model.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reconnect {
    private String name;
    private String token;
    private Integer character;
    private String world;
}
