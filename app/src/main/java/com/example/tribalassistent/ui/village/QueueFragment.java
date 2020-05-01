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
import com.example.tribalassistent.Service.building.Queue;


public class QueueFragment extends Fragment {
    private static final String TAG = "QueueFragment";
    private int village_id;
    private Context mContext;
    private ListView listView;
    private QueueListAdapter listAdapter;

    public QueueFragment(int village_id) {
        this.village_id = village_id;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_queue, container, false);
        mContext = requireActivity();
        listView = view.findViewById(R.id.building_queue);
        VillageViewModel villageViewModel = ViewModelProviders.of(requireActivity()).get(VillageViewModel.class);
        villageViewModel.getQueues().observe(requireActivity(), queues -> update(queues.get(village_id)));
        return view;
    }

    private void setupListView(Queue queue) {
        listAdapter = new QueueListAdapter(mContext, R.layout.layout_world_selection, queue, village_id);
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

