package com.example.tribalassistent.ui.village;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tribalassistent.R;
import com.example.tribalassistent.Service.building.Manager;

import java.util.List;

public class QueueListAdapter extends ArrayAdapter<String> {
    private static final String TAG = "QueueListAdapter";
    private int villageId;
    private int mResource;

    public QueueListAdapter(@NonNull Context context, int resource, @NonNull List<String> objects, int villageId) {
        super(context, resource, objects);
        this.villageId = villageId;
        this.mResource = resource;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final String buildingName = getItem(position);

        LayoutInflater inflater = LayoutInflater.from(this.getContext());
        convertView = inflater.inflate(mResource, parent, false);

        Button button = convertView.findViewById(R.id.buttonPanel);
        button.setText("Cancel");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Manager.getInstance().getQueue(villageId).remove(position);
            }
        });
        TextView textView = convertView.findViewById(R.id.textView);
        textView.setText(buildingName);
        return convertView;
    }

}
