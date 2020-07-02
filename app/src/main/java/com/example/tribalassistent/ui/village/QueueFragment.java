package com.example.tribalassistent.ui.village;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.tribalassistent.R;
import com.example.tribalassistent.client.service.building.Queue;
import com.example.tribalassistent.data.model.building.Job;

import java.util.ArrayList;
import java.util.List;


public class QueueFragment extends Fragment {
    private static final String TAG = "QueueFragment";
    private int village_id;
    private Context mContext;
    private ListView waitingListView;
    private ListView buildingListView;
    private WaitingListAdapter waitAdapter;
    private BuildingListAdapter buildAdapter;

    public QueueFragment(int village_id) {
        this.village_id = village_id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_queue, container, false);
        mContext = requireActivity();
        waitingListView = view.findViewById(R.id.building_waiting);
        buildingListView = view.findViewById(R.id.building_queue);
        VillageViewModel villageViewModel = ViewModelProviders.of(requireActivity()).get(VillageViewModel.class);
        villageViewModel.getQueues().observe(requireActivity(), queues -> updateWait(queues.get(village_id)));
        villageViewModel.getVillageGameBatch().observe(requireActivity(), villageGameBatch -> updateBuild(villageGameBatch.get(village_id).getBuildingQueue().getQueue()));
        return view;
    }

    private void setupWaitListView(Queue queue) {
        waitAdapter = new WaitingListAdapter(mContext, R.layout.layout_world_selection, queue, village_id);
        waitingListView.setAdapter(waitAdapter);
    }

    public void updateWait(Queue queue) {
        Log.d(TAG, "Data updating... ");
        if (waitAdapter == null) {
            setupWaitListView(queue);
        }
        waitAdapter.notifyDataSetChanged();
    }

    private void setupBuildListView(List<Job> queue) {
        List<String> jobs = new ArrayList<>();
        for (Job job : queue) {
            jobs.add(job.getBuilding());
        }
        buildAdapter = new BuildingListAdapter(mContext, R.layout.layout_world_selection, jobs);
        buildingListView.setAdapter(buildAdapter);
    }

    public void updateBuild(List<Job> queue) {
        Log.d(TAG, "Data updating... ");
        if (buildAdapter == null) {
            setupBuildListView(queue);
        }
        buildAdapter.notifyDataSetChanged();
    }
}

