package com.example.tribalassistent.ui.character;

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
import com.example.tribalassistent.data.model.authentication.Character;
import com.example.tribalassistent.data.repositories.LoginRepository;

import java.util.List;

public class CharacterListAdapter extends ArrayAdapter<Character> {
    private int mResource;

    public CharacterListAdapter(@NonNull Context context, int resource, @NonNull List<Character> objects) {
        super(context, resource, objects);
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Character character = getItem(position);
        String name = character.getWorld_name();
        LayoutInflater inflater = LayoutInflater.from(this.getContext());
        convertView = inflater.inflate(mResource, parent, false);

        TextView textView = convertView.findViewById(R.id.textView);
        Button button = convertView.findViewById(R.id.buttonPanel);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginRepository.getInstance().select(character.getCharacter_id(), character.getWorld_id());
                //System.out.println(GameDataBatch.getGameData());
            }
        });
        textView.setText(name);
        return convertView;
    }
}
