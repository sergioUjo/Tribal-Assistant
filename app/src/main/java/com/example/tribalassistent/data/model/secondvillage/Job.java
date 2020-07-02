package com.example.tribalassistent.data.model.secondvillage;

import com.example.tribalassistent.data.model.common.Resources;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class Job {
    private String building_graphic;
    private Map<String, Integer> buildings_required;
    private boolean collected;
    private Resources costs;
    private Integer day;
    private Integer duration;
    private String id;
    private List<String> jobs_required;
    private Integer position;
    private Object rewards;
    private Boolean takes_until_end_of_day;
    private Long time_completed;
    private Long time_started;
}
