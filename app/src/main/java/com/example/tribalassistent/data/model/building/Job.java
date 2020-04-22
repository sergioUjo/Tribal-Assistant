package com.example.tribalassistent.data.model.building;

import lombok.Data;

@Data
public class Job {
    private Integer id;
    private String building;
    private Integer level;
    private Long time_started;
    private Long time_completed;
    private String type;
}
