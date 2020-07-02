package com.example.tribalassistent.ui.village;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tribalassistent.client.service.building.Manager;
import com.example.tribalassistent.client.service.building.Queue;
import com.example.tribalassistent.data.model.village.VillageGameBatch;
import com.example.tribalassistent.data.repositories.VillageRepository;

import java.util.Map;

public class VillageViewModel extends ViewModel {
    private MutableLiveData<Map<Integer, Queue>> queues = new MutableLiveData<>();
    private MutableLiveData<VillageGameBatch> villageGameBatch = new MutableLiveData<>();

    public VillageViewModel() {
        VillageRepository.getInstance().addObserver((o, arg) -> villageGameBatch.postValue((VillageGameBatch) arg));
        Manager.getInstance().addObserver((o, arg) -> queues.postValue((Map<Integer, Queue>) arg));
    }

    public MutableLiveData<Map<Integer, Queue>> getQueues() {
        return queues;
    }

    public LiveData<VillageGameBatch> getVillageGameBatch() {
        return villageGameBatch;
    }


}
