package com.example.tribalassistent.data.model.authentication;

import lombok.Data;

@Data
class World {
    private String id;
    private String name;
    private boolean full;
    private int recommended;
    private boolean key_required;
}
