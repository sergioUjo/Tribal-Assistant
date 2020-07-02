package com.example.tribalassistent.data.model.secondvillage;

import java.util.Map;

import lombok.Data;

@Data
public class Info {
    private Coordinates coordinates;
    private Integer day;
    private Long end_of_day;
    private Long expiration_time;
    private Map<String, Job> jobs;
    private String village_name;
}
