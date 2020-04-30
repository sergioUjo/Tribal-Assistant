package com.example.tribalassistent.Service.building;

import android.util.Log;

import androidx.annotation.Nullable;

import com.example.tribalassistent.data.comunication.EventType;
import com.example.tribalassistent.data.comunication.Observer;
import com.example.tribalassistent.data.comunication.OnResultListener;
import com.example.tribalassistent.data.comunication.Result;
import com.example.tribalassistent.data.comunication.Subject;
import com.example.tribalassistent.data.model.building.Upgrading;
import com.example.tribalassistent.data.model.common.BuildingName;
import com.example.tribalassistent.data.repositories.VillageRepository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Queue extends ArrayList<String> implements Subject, OnResultListener<Upgrading> {
    private static final String TAG = "Queue";
    private static final Builder BUILDER = new Builder();
    private static List<String> infiniteQueue;
    private List<Observer> observers;
    private boolean auto;
    private int villageId;

    static {
        infiniteQueue = new LinkedList<>();
        for (BuildingName building : BuildingName.values()) {
            infiniteQueue.add(building.getName());
        }
    }

    public Queue(int villageId) {
        super();
        this.villageId = villageId;
        observers = new ArrayList<>();
        BUILDER.setOnEventListener(this);
    }


    @Override
    public boolean add(String building) {
        Log.d(TAG, "Added " + building);
        boolean result = super.add(building);
        build();
        notifyObservers();
        return result;
    }

    @Override
    public String remove(int index) {
        String result = super.remove(index);
        Log.d(TAG, "Removed " + result);
        notifyObservers();
        return result;
    }

    @Override
    public boolean remove(@Nullable Object o) {
        boolean result = super.remove(o);
        Log.d(TAG, "Removed " + o.toString());
        notifyObservers();
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

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public Object getEvent(EventType eventType) {
        return null;
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    @Override
    public void onResult(Result<Upgrading> result) {
        try {
            Upgrading upgrading = result.getData();
            this.remove(upgrading.getJob().getBuilding());
            VillageRepository.getInstance().addJob(villageId, upgrading.getJob());
        } catch (NoSuchFieldException e) {
            Log.d(TAG, e.getMessage());
        }
    }
}
