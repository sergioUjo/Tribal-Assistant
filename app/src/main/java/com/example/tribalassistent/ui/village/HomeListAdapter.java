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
import com.example.tribalassistent.Service.building.Manager;

import java.util.List;

public class HomeListAdapter extends ArrayAdapter<String> {
    private static final String TAG = "HomeListAdapter";
    private static final String ADDED = "Building added to village queue.";
    private int villageId;
    private int mResource;
    private Context mContext;

    public HomeListAdapter(@NonNull Context context, int resource, @NonNull List<String> objects, int villageId) {
        super(context, resource, objects);
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

        TextView textView = convertView.findViewById(R.id.building_name);
        Button button = convertView.findViewById(R.id.build_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Building " + buildingName + " was added to " + villageId + " queue.");
                Toast.makeText(mContext, ADDED, Toast.LENGTH_SHORT).show();
                Manager.getInstance().getQueue(villageId).add(buildingName);
            }
        });
        textView.setText(buildingName);
        return convertView;
    }

}
