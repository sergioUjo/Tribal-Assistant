package com.example.tribalassistent.Service.building;

import android.util.Log;

import com.example.tribalassistent.data.comunication.request.CompleteRequest;
import com.example.tribalassistent.data.comunication.request.OnResultListener;
import com.example.tribalassistent.data.comunication.request.Result;
import com.example.tribalassistent.data.comunication.request.UpgradeRequest;
import com.example.tribalassistent.data.model.building.Job;
import com.example.tribalassistent.data.model.building.Upgrading;
import com.example.tribalassistent.data.model.common.BuildingName;
import com.example.tribalassistent.data.repositories.CharacterRepository;
import com.example.tribalassistent.data.repositories.Observer;
import com.example.tribalassistent.data.repositories.Subject;
import com.example.tribalassistent.data.repositories.VillageRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Manager implements Runnable, OnResultListener<Upgrading>, Subject<Map<Integer, Queue>> {
    private static final String TAG = "Manager";
    private static final int INITIAL_TIME = 60;
    private static final int DELAY = 3600;
    private static final ScheduledExecutorService WORKER = Executors.newSingleThreadScheduledExecutor();
    private static Manager instance;
    private List<Observer<Map<Integer, Queue>>> observers;
    private Map<Integer, Queue> queues;

    private Manager() {
        queues = new HashMap<>();
        for (int villageId : CharacterRepository.getInstance().getVillageIds()) {
            queues.put(villageId, new Queue());
        }
        observers = new LinkedList<>();
        WORKER.scheduleWithFixedDelay(this, INITIAL_TIME, DELAY, TimeUnit.SECONDS);
    }

    public static Manager getInstance() {
        if (instance == null) {
            instance = new Manager();
        }
        return instance;
    }

    public Queue getQueue(int village_id) {
        return queues.get(village_id);
    }

    public void add(int village_id, String building) {
        getQueue(village_id).add(building);
        build(village_id);
        notifyObservers();
    }

    public void remove(int village_id, int index) {
        getQueue(village_id).remove(index);
        notifyObservers();
    }

    public void remove(int village_id, String building) {
        getQueue(village_id).remove(building);
        notifyObservers();
    }

    @Override
    public void run() {
        for (Integer village_id : queues.keySet()) {
            build(village_id);
        }
    }

    public void build(Integer village_id) {
        for (String building : queues.get(village_id)) {
            build(village_id, building);
        }
    }

    public void build(int village_id, String buildingName) {
        UpgradeRequest request = new UpgradeRequest(buildingName, village_id);
        request.onResultListener(this);
        request.doInBackground();
    }

    @Override
    public void onResult(Result<Upgrading> result) {
        try {
            Upgrading upgrading = result.getData();
            remove(upgrading.getVillage_id(), upgrading.getJob().getBuilding());
            VillageRepository.getInstance().addJob(upgrading.getVillage_id(), upgrading.getJob());
            completeInstantly(upgrading.getJob(), upgrading.getVillage_id());
        } catch (NoSuchFieldException e) {
            Log.d(TAG, e.getMessage());
        }
    }

    private void completeInstantly(Job job, int village_id) {
        int headQuarterLevel = VillageRepository.getInstance().getVillageData().get(village_id).getVillage().getBuildings().get(BuildingName.HEAD_QUARTER.getName()).getLevel();
        long now = new Date().getTime() / 1000;
        long delay = job.getTime_completed() - now - headQuarterLevel * 30;
        WORKER.schedule(() -> {
            CompleteRequest request = new CompleteRequest(job.getId(), village_id);
            request.doInBackground();
        }, delay, TimeUnit.SECONDS);
    }

    @Override
    public void observe(Observer<Map<Integer, Queue>> observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer<Map<Integer, Queue>> observer : observers) {
            observer.update(queues);
        }
    }
}
