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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tribalassistent.R;
import com.example.tribalassistent.Service.building.Queue;

import java.util.Map;


public class QueueFragment extends Fragment {
    private static final String TAG = "QueueFragment";
    private VillageViewModel villageViewModel;
    private Context mContext;
    private ListView listView;
    private QueueListAdapter listAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_queue, container, false);
        mContext = requireActivity();
        listView = view.findViewById(R.id.building_queue);

        villageViewModel = ViewModelProviders.of(requireActivity()).get(VillageViewModel.class);
        villageViewModel.getQueues().observe(requireActivity(), new Observer<Map<Integer, Queue>>() {
            @Override
            public void onChanged(Map<Integer, Queue> queues) {
                update(queues.get(69));
            }
        });
        return view;
    }

    private void setupListView(Queue queue) {
        listAdapter = new QueueListAdapter(mContext, R.layout.layout_world_selection, queue, queue.getVillageId());
        listView.setAdapter(listAdapter);
    }

    public void update(Queue queue) {
        Log.d(TAG, "Data updating... ");
        if (listAdapter == null) {
            setupListView(queue);
        }
        listAdapter.notifyDataSetChanged();
    }
}

