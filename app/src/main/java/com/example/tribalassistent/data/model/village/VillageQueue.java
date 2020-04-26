package com.example.tribalassistent.data.model.village;

import com.example.tribalassistent.data.model.building.Job;

import java.util.List;

import lombok.Data;

@Data
public class VillageQueue {
    private Integer village_id;
    private Integer unlocked_slots;
    private List<Job> queue;
}
