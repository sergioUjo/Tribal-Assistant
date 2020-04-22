package com.example.tribalassistent.data.model.system;

import java.util.List;

import lombok.Data;

@Data
public
class Error {
    private String cause;
    private String code;
    private List<String> details;
    private String message;
    private String error_code;
    private String name;
}

