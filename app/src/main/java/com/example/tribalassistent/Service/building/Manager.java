package com.example.tribalassistent.Service.building;

import com.example.tribalassistent.data.repositories.CharacterRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Manager implements Runnable {
    private static final ScheduledExecutorService WORKER = Executors.newSingleThreadScheduledExecutor();
    private static final int INITIAL_TIME = 0;
    private static final int DELAY = 300;
    private static Manager instance;
    private List<Queue> queues;

    private Manager() {
        queues = new LinkedList<>();
        for (int villageId : CharacterRepository.getInstance().getVillageIds()) {
            queues.add(new Queue(villageId));
        }
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
        for (Queue queue : queues) {
            queue.build();
        }
    }

    public void run(Integer villageId) {
        for (Queue queue : queues) {
            if (queue.getVillageId() == villageId) {
                queue.build();
            }
        }
    }
}
