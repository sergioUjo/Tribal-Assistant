package com.example.tribalassistent.Service.building;

import com.example.tribalassistent.data.model.common.BuildingName;

import java.util.LinkedList;
import java.util.List;

import lombok.Getter;

public class Queue {
    private static final Builder BUILDER = Builder.getInstance();
    private static List<String> infiniteQueue;
    private List<String> userQueue;
    private boolean auto;
    @Getter
    private int villageId;

    static {
        infiniteQueue = new LinkedList<>();
        for (BuildingName building : BuildingName.values()) {
            infiniteQueue.add(building.getName());
        }
    }

    public Queue(int villageId) {
        this.villageId = villageId;
        userQueue = new LinkedList<>();
    }

    public void add(String building) {
        userQueue.add(building);
        build();
    }

    public void build() {
        for (String building : userQueue) {
            if (BUILDER.build(building, villageId)) {
                userQueue.remove(building);
                return;
            }
        }
        if (auto) {
            for (String building : infiniteQueue) {
                if (BUILDER.build(building, villageId)) {
                    return;
                }
            }
        }
    }

}
