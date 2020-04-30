package com.example.tribalassistent.data.repositories;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.tribalassistent.Service.building.Manager;
import com.example.tribalassistent.data.comunication.EventType;
import com.example.tribalassistent.data.comunication.Observer;
import com.example.tribalassistent.data.comunication.OnResultListener;
import com.example.tribalassistent.data.comunication.Result;
import com.example.tribalassistent.data.comunication.SocketNotification;
import com.example.tribalassistent.data.comunication.SocketRequest;
import com.example.tribalassistent.data.comunication.Subject;
import com.example.tribalassistent.data.model.village.LevelChange;
import com.example.tribalassistent.data.model.village.Village;
import com.example.tribalassistent.data.model.village.VillageData;
import com.example.tribalassistent.data.model.village.VillageGameBatch;
import com.example.tribalassistent.data.model.village.VillageIds;

import lombok.Setter;
import lombok.SneakyThrows;

@Setter
public class VillageRepository implements Observer {
    private static final String TAG = "VillageRepository";
    private static VillageRepository instance;
    private MutableLiveData<VillageGameBatch> villageData;


    private VillageRepository() {
        SocketNotification.getInstance().registerObserver(this);
        villageData = new MutableLiveData<>();
    }

    public static VillageRepository getInstance() {
        if (instance == null) {
            instance = new VillageRepository();
        }
        return instance;
    }


    private void requestVillageData() {
        SocketRequest<VillageIds, VillageGameBatch> request = new SocketRequest<>();
        request.setOnResultListener(new OnResultListener<VillageGameBatch>() {
            @SneakyThrows
            @Override
            public void onResult(Result<VillageGameBatch> villageResult) {
                villageData.setValue(villageResult.getData());
            }
        });
        request.doInBackground(new VillageIds(CharacterRepository.getInstance().getVillageIds()), EventType.GET_VILLAGE_DATA);
    }

    private VillageData getVillageData(int villageId) {
        return villageData.getValue().get(villageId);
    }

    public MutableLiveData<VillageGameBatch> getVillageData() {
        if (villageData.getValue() == null) {
            requestVillageData();
        }
        return villageData;
    }


    @Override
    public void update(Subject observable) {
        Log.d(TAG, "Updating... ");
        resourceChanged((Village) observable.getEvent(EventType.VILLAGE_RESOURCE_CHANGED));
        levelChange((LevelChange) observable.getEvent(EventType.BUILDING_LEVEL_CHANGE));
        villageData.setValue(villageData.getValue());
    }

    private void levelChange(LevelChange remote) {
        if (remote != null) {
            Village local = getVillageData(remote.getVillage_id()).getVillage();
            local.getBuildings().get(remote.getBuilding()).setLevel(remote.getLevel());
            Manager.getInstance().getQueue(remote.getVillage_id()).build();
            Manager.getInstance().run(remote.getVillage_id());
        }
    }

    private void resourceChanged(Village remote) {
        if (remote != null) {
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
}
