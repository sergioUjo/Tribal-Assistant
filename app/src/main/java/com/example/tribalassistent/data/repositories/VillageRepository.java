package com.example.tribalassistent.data.repositories;

import android.util.Log;

import com.example.tribalassistent.Service.building.Manager;
import com.example.tribalassistent.data.comunication.EventMsg;
import com.example.tribalassistent.data.comunication.Observer;
import com.example.tribalassistent.data.comunication.request.VillageGameBatchRequest;
import com.example.tribalassistent.data.model.building.Job;
import com.example.tribalassistent.data.model.village.LevelChange;
import com.example.tribalassistent.data.model.village.Village;
import com.example.tribalassistent.data.model.village.VillageData;
import com.example.tribalassistent.data.model.village.VillageGameBatch;

public class VillageRepository implements Observer<EventMsg> {
    private static final String TAG = "VillageRepository";
    private static VillageRepository instance;
    private VillageGameBatch villageData;


    private VillageRepository() {
        VillageGameBatchRequest request = new VillageGameBatchRequest(CharacterRepository.getInstance().getVillageIds());
        request.onResultListener(villageResult -> {
            try {
                villageData = villageResult.getData();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        });
        request.doInBackground();
    }

    public static VillageRepository getInstance() {
        if (instance == null) {
            instance = new VillageRepository();
        }
        return instance;
    }

    private VillageData getVillageData(int villageId) {
        return villageData.get(villageId);
    }

    public void addJob(int village_id, Job job) {
        getVillageData(village_id).getBuildingQueue().getQueue().add(job);
        villageData.postValue(villageData.getValue());
    }

    @Override
    public void update(EventMsg eventMsg) {
        Log.d(TAG, "Updating... ");
        //resourceChanged(eventMsg);
        //levelChange(eventMsg);
        villageData.postValue(villageData.getValue());
    }

    private void levelChange(LevelChange remote) {
        VillageData data = getVillageData(remote.getVillage_id());
        data.getBuildingQueue().getQueue().remove(0);
        Village local = data.getVillage();
        local.getBuildings().get(remote.getBuilding()).setLevel(remote.getLevel());
        Manager.getInstance().run(remote.getVillage_id());
    }

    private void resourceChanged(Village remote) {
        Village local = getVillageData(remote.getVillageId()).getVillage();
        local.setBase_storage(remote.getBase_storage());
        local.setBuilding_queue_slots(remote.getBuilding_queue_slots());
        local.setLoyalty(remote.getLoyalty());
        local.setProduction_rates(remote.getProduction_rates());
        local.setRes_last_update(remote.getRes_last_update());
        local.setResources(remote.getResources());
        local.setStorage(remote.getStorage());
    }
}
