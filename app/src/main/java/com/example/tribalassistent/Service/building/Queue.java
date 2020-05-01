package com.example.tribalassistent.Service.building;

import android.util.Log;

import com.example.tribalassistent.data.model.common.BuildingName;

import java.util.ArrayList;
import java.util.List;

public class Queue extends ArrayList<String> {
    private static final String TAG = "Queue";
    private static final Builder BUILDER = Builder.getInstance();
    private static List<String> infiniteQueue = BuildingName.getNames();
    private boolean auto;
    private int villageId;

    public int getVillageId() {
        return villageId;
    }

    public Queue(int villageId) {
        super();
        this.villageId = villageId;
    }

    @Override
    public boolean add(String building) {
        Log.d(TAG, "Added " + building);
        boolean result = super.add(building);
        build();
        return result;
    }

    @Override
    public String remove(int index) {
        String result = super.remove(index);
        Log.d(TAG, "Removed " + result);
        return result;
    }

    public void build() {
        for (String building : this) {
            BUILDER.build(building, villageId);
        }
        if (auto) {
            for (String building : infiniteQueue) {
                BUILDER.build(building, villageId);
            }
        }
    }
}
