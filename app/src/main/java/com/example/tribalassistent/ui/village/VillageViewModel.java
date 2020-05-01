package com.example.tribalassistent.ui.village;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tribalassistent.Service.building.Queue;
import com.example.tribalassistent.data.model.village.VillageGameBatch;
import com.example.tribalassistent.data.repositories.VillageRepository;

import java.util.Map;

public class VillageViewModel extends ViewModel {
    private MutableLiveData<Map<Integer, Queue>> queues;
    private LiveData<VillageGameBatch> villageGameBatch = VillageRepository.getInstance().getVillageData();

    public MutableLiveData<Map<Integer, Queue>> getQueues() {
        return queues;
    }

    public void queueRemove(int village_id, int index) {
        queues.getValue().get(village_id).remove(index);
        queues.setValue(queues.getValue());
    }

    public LiveData<VillageGameBatch> getVillageGameBatch() {
        return villageGameBatch;
    }
}
