package com.example.tribalassistent.Service.building;

import android.util.Log;

import com.example.tribalassistent.data.comunication.Observer;
import com.example.tribalassistent.data.comunication.OnResultListener;
import com.example.tribalassistent.data.comunication.Subject;
import com.example.tribalassistent.data.comunication.request.Result;
import com.example.tribalassistent.data.model.building.Upgrading;
import com.example.tribalassistent.data.repositories.CharacterRepository;
import com.example.tribalassistent.data.repositories.VillageRepository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Manager implements Runnable, OnResultListener<Upgrading>, Subject<Map<Integer, Queue>> {
    private static final String TAG = "Manager";
    private static final ScheduledExecutorService WORKER = Executors.newSingleThreadScheduledExecutor();
    private static final int INITIAL_TIME = 0;
    private static final int DELAY = 3600;
    private static Manager instance;
    private List<Observer<Map<Integer, Queue>>> observers;
    private Map<Integer, Queue> queues;

    private Manager() {
        queues = new HashMap<>();
        for (int villageId : CharacterRepository.getInstance().getVillageIds()) {
            queues.put(villageId, new Queue(villageId));
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

    @Override
    public void run() {
        for (Queue queue : queues.values()) {
            queue.build();
        }
    }

    public void run(Integer villageId) {
        getQueue(villageId).build();
    }

    public Queue getQueue(int villageId) {
        return queues.get(villageId);
    }

    public void add(int village_id, String building) {
        getQueue(village_id).add(building);
        notifyObservers(queues);
    }

    public void remove(int village_id, int index) {
        getQueue(village_id).remove(index);
        notifyObservers(queues);
    }

    public void remove(int village_id, String building) {
        Queue queue = getQueue(village_id);
        remove(village_id, queue.indexOf(building));
    }

    @Override
    public void onResult(Result<Upgrading> result) {
        try {
            Upgrading upgrading = result.getData();
            remove(upgrading.getVillage_id(), upgrading.getJob().getBuilding());
            VillageRepository.getInstance().addJob(upgrading.getVillage_id(), upgrading.getJob());
        } catch (NoSuchFieldException e) {
            Log.d(TAG, e.getMessage());
        }
    }


    @Override
    public void observe(Observer<Map<Integer, Queue>> observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers(Map<Integer, Queue> event) {
        for (Observer<Map<Integer, Queue>> observer : observers) {
            observer.update(event);
        }
    }
}
