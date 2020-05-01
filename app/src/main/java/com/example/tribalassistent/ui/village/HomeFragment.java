package com.example.tribalassistent.ui.village;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tribalassistent.R;
import com.example.tribalassistent.data.model.village.Building;
import com.example.tribalassistent.data.model.village.VillageData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    private Context mContext;
    private HomeListAdapter listAdapter;
    private GridView gridView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mContext = requireActivity();
        gridView = view.findViewById(R.id.building_grid_view);
        return view;
    }

    public void update(VillageData villageData) {
        Log.d(TAG, "Data updating... ");
        if (listAdapter == null) {
            setupGridView(villageData);
        } else {
            listAdapter.notifyDataSetChanged();
        }
    }

    private void setupGridView(VillageData villageData) {
        Map<String, Building> buildings = villageData.getVillage().getBuildings();
        List<String> names = new ArrayList<>(buildings.keySet());
        listAdapter = new HomeListAdapter(mContext, R.layout.layout_building, names, buildings, villageData.getVillage().getVillageId());
        gridView.setAdapter(listAdapter);
    }
}
