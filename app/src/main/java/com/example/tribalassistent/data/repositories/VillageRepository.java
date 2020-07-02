package com.example.tribalassistent.data.repositories;

import com.example.tribalassistent.client.AsyncCallback;
import com.example.tribalassistent.client.BuilderServiceAsync;
import com.example.tribalassistent.client.OnSuccess;
import com.example.tribalassistent.client.VillageServiceAsync;
import com.example.tribalassistent.client.service.building.Manager;
import com.example.tribalassistent.data.model.building.Complete;
import com.example.tribalassistent.data.model.building.Job;
import com.example.tribalassistent.data.model.building.Upgrade;
import com.example.tribalassistent.data.model.building.Upgrading;
import com.example.tribalassistent.data.model.village.LevelChange;
import com.example.tribalassistent.data.model.village.Village;
import com.example.tribalassistent.data.model.village.VillageData;
import com.example.tribalassistent.data.model.village.VillageGameBatch;
import com.example.tribalassistent.data.model.village.VillageIds;

import java.util.List;

public class VillageRepository extends BaseRepository implements BuilderServiceAsync, VillageServiceAsync {
    private static final String TAG = "VillageRepository";
    private static VillageRepository instance;
    private VillageGameBatch villageData;

    public VillageGameBatch getVillageData() {
        return villageData;
    }

    private VillageRepository() {
        getVillageData(CharacterRepository.getInstance().getVillageIds(), new OnSuccess<VillageGameBatch>() {
            @Override
            public void onSuccess(VillageGameBatch result) {
                villageData = result;
                notifyObservers();
            }
        });
    }

    public static VillageRepository getInstance() {
        if (instance == null) {
            instance = new VillageRepository();
        }
        return instance;
    }

    public List<Job> getBuildingQueue(int villageId) {
        return getVillageData(villageId).getBuildingQueue().getQueue();
    }

    public VillageData getVillageData(int villageId) {
        return villageData.get(villageId);
    }

    public void addJob(int village_id, Job job) {
        getBuildingQueue(village_id).add(job);
        notifyObservers();
    }

    public void levelChanged(LevelChange remote) {
        VillageData data = getVillageData(remote.getVillage_id());
        data.getBuildingQueue().getQueue().remove(0);
        Village local = data.getVillage();
        local.getBuildings().get(remote.getBuilding()).setLevel(remote.getLevel());
        Manager.getInstance().build(remote.getVillage_id());
        notifyObservers();
    }

    public void resourceChanged(Village remote) {
        Village local = getVillageData(remote.getVillageId()).getVillage();
        local.setBase_storage(remote.getBase_storage());
        local.setBuilding_queue_slots(remote.getBuilding_queue_slots());
        local.setLoyalty(remote.getLoyalty());
        local.setProduction_rates(remote.getProduction_rates());
        local.setRes_last_update(remote.getRes_last_update());
        local.setResources(remote.getResources());
        local.setStorage(remote.getStorage());
    }

    @Override
    public void notifyObservers() {
        setChanged();
        super.notifyObservers(villageData);
    }

    @Override
    public void completeInstantly(int jobId, int villageId) {
        socketConnection.send(new Complete(jobId, villageId));
    }

    @Override
    public void upgrade(String buildingName, int villageId, AsyncCallback<Upgrading> async) {
        socketConnection.send(new Upgrade(buildingName, villageId), async);
    }

    @Override
    public void getVillageData(List<Integer> villageIds, AsyncCallback<VillageGameBatch> async) {
        socketConnection.send(new VillageIds(villageIds), async);
    }
}
