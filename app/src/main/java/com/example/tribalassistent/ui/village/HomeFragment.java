package com.example.tribalassistent.ui.village;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tribalassistent.R;
import com.example.tribalassistent.data.model.common.BuildingName;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    private Context mContext;
    private int villageId;

    public HomeFragment(int villageId) {
        super();
        this.villageId = villageId;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mContext = getActivity();
        setupGridView((GridView) view.findViewById(R.id.building_grid_view));

        return view;
    }

    private void setupGridView(GridView gridView) {
        List<String> names = new ArrayList<>();
        for (BuildingName name : BuildingName.values()) {
            names.add(name.getName());
        }
        HomeListAdapter listAdapter = new HomeListAdapter(mContext, R.layout.layout_building, names, villageId);
        gridView.setAdapter(listAdapter);
    }
}
