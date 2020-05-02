package com.example.tribalassistent.ui.village;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tribalassistent.R;
import com.example.tribalassistent.data.model.village.Building;
import com.example.tribalassistent.service.building.Manager;

import java.util.List;
import java.util.Map;

public class HomeListAdapter extends ArrayAdapter<String> {
    private static final String TAG = "HomeListAdapter";
    private static final String ADDED = "Building added to village queue.";
    private final Map<String, Building> buildings;
    private int villageId;
    private int mResource;
    private Context mContext;

    public HomeListAdapter(@NonNull Context context, int resource, @NonNull List<String> objects, Map<String, Building> buildings, int villageId) {
        super(context, resource, objects);
        this.buildings = buildings;
        this.villageId = villageId;
        this.mResource = resource;
        this.mContext = context;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final String buildingName = getItem(position);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView nameTextView = convertView.findViewById(R.id.building_name);
        TextView levelTextView = convertView.findViewById(R.id.building_level);
        Button button = convertView.findViewById(R.id.build_button);

        nameTextView.setText(buildingName);
        levelTextView.setText(buildings.get(buildingName).getLevel().toString());
        button.setOnClickListener(v -> {
            Log.d(TAG, "Building " + buildingName + " was added to " + villageId + " queue.");
            Toast.makeText(mContext, ADDED, Toast.LENGTH_SHORT).show();
            Manager.getInstance().add(villageId, buildingName);
        });
        return convertView;
    }

}
