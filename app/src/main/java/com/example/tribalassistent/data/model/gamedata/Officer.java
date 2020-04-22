package com.example.tribalassistent.data.model.gamedata;

import java.util.List;

import lombok.Data;

@Data
class Officer {
    private List<String> command_types;
    private List<String> exclude_unit_types;
    private List<Ability> abilities;
}
