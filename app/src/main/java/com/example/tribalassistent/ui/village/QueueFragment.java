package com.example.tribalassistent.ui.village;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tribalassistent.R;
import com.example.tribalassistent.Service.building.Manager;
import com.example.tribalassistent.Service.building.Queue;
import com.example.tribalassistent.data.comunication.Observer;
import com.example.tribalassistent.data.comunication.Subject;


public class QueueFragment extends Fragment implements Observer {
    private static final String TAG = "QueueFragment";
    private int villageId;
    private Context mContext;
    private Queue queue;
    private QueueListAdapter listAdapter;


    public QueueFragment(int villageId) {
        super();
        this.villageId = villageId;
        queue = Manager.getInstance().getQueue(villageId);
        queue.registerObserver(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_queue, container, false);
        this.mContext = getActivity();
        setupListView((ListView) view.findViewById(R.id.building_queue));
        return view;
    }

    private void setupListView(ListView listView) {
        listAdapter = new QueueListAdapter(mContext, R.layout.layout_world_selection, queue, villageId);
        listView.setAdapter(listAdapter);
    }

    @Override
    public void update(Subject observable) {
        listAdapter.notifyDataSetChanged();
    }
}

