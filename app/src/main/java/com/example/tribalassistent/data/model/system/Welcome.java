package com.example.tribalassistent.data.model.system;

import lombok.Data;

@Data
public
class Welcome {
    private String message;
    private String transport;
    private String host;
    private boolean maintenance;
}
