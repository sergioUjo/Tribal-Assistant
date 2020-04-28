package com.example.tribalassistent.Service.building;

import com.example.tribalassistent.data.repositories.CharacterRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Manager implements Runnable {
    private static final ScheduledExecutorService WORKER = Executors.newSingleThreadScheduledExecutor();
    private static final int INITIAL_TIME = 0;
    private static final int DELAY = 300;
    private static CharacterRepository characterRepository = CharacterRepository.getInstance();
    private static Manager instance;
    private Map<Integer, Queue> queues;

    private Manager() {
        queues = new HashMap<>();
        for (int villageId : characterRepository.getVillageIds()) {
            queues.put(villageId, new Queue(villageId));
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
        for (Queue queue : queues.values()) {
            queue.build();
        }
    }

    public void run(Integer villageId) {
        queues.get(villageId).build();
    }

    public Queue getQueue(int villageId) {
        return queues.get(villageId);
    }

}
