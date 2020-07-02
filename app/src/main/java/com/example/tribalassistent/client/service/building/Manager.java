package com.example.tribalassistent.client.service.building;

import android.util.Log;

import com.example.tribalassistent.client.OnSuccess;
import com.example.tribalassistent.data.model.building.Job;
import com.example.tribalassistent.data.model.building.Upgrading;
import com.example.tribalassistent.data.model.common.BuildingName;
import com.example.tribalassistent.data.repositories.CharacterRepository;
import com.example.tribalassistent.data.repositories.VillageRepository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Manager extends Observable implements Runnable {
    private static final String TAG = "Manager";
    private static final int INITIAL_TIME = 60;
    private static final int DELAY = 3600;
    private static final ScheduledExecutorService WORKER = Executors.newSingleThreadScheduledExecutor();
    private static Manager instance;
    private Map<Integer, Queue> queues;

    private Manager() {
        queues = new HashMap<>();
        for (int villageId : CharacterRepository.getInstance().getVillageIds()) {
            queues.put(villageId, new Queue());
        }
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

    public void build(int villageId) {
        Log.d(TAG, villageId + " queue size " + VillageRepository.getInstance().getBuildingQueue(villageId).size());
        if (VillageRepository.getInstance().getBuildingQueue(villageId).size() < 2) {
            for (String building : queues.get(villageId)) {
                VillageRepository.getInstance().upgrade(building, villageId, new OnSuccess<Upgrading>() {
                    @Override
                    public void onSuccess(Upgrading result) {
                        onUpgrading(result);
                    }
                });
            }
        }
    }


    private void onUpgrading(Upgrading upgrading) {
        remove(upgrading.getVillage_id(), upgrading.getJob().getBuilding());
        VillageRepository.getInstance().addJob(upgrading.getVillage_id(), upgrading.getJob());
        completeInstantly(upgrading.getJob(), upgrading.getVillage_id());
    }

    private void completeInstantly(Job job, int village_id) {
        int headQuarterLevel = VillageRepository.getInstance().getVillageData(village_id).getVillage().getBuildings().get(BuildingName.HEAD_QUARTER.getName()).getLevel();
        long now = new Date().getTime() / 1000;
        long delay = job.getTime_completed() - now - headQuarterLevel * 30;
        WORKER.schedule(() -> VillageRepository.getInstance().completeInstantly(job.getId(), village_id), delay, TimeUnit.SECONDS);
    }

    @Override
    public void notifyObservers() {
        setChanged();
        super.notifyObservers(queues);
    }
}
