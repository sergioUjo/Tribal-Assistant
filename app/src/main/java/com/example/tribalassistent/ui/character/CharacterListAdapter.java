package com.example.tribalassistent.ui.character;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tribalassistent.R;
import com.example.tribalassistent.client.OnSuccess;
import com.example.tribalassistent.data.model.authentication.Character;
import com.example.tribalassistent.data.model.authentication.CharacterSelected;
import com.example.tribalassistent.data.model.character.CharacterInfo;
import com.example.tribalassistent.data.repositories.CharacterRepository;
import com.example.tribalassistent.data.repositories.LoginRepository;
import com.example.tribalassistent.data.repositories.SecondVillageRepository;
import com.example.tribalassistent.ui.village.VillageActivity;

import java.util.List;

public class CharacterListAdapter extends ArrayAdapter<Character> {
    private static final LoginRepository LOGIN = LoginRepository.getInstance();
    private int mResource;


    public CharacterListAdapter(@NonNull Context context, int resource, @NonNull List<Character> objects) {
        super(context, resource, objects);
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Character character = getItem(position);

        LayoutInflater inflater = LayoutInflater.from(this.getContext());

        convertView = inflater.inflate(mResource, parent, false);

        TextView textView = convertView.findViewById(R.id.textView);
        textView.setText(character.getWorld_name());
        Button button = convertView.findViewById(R.id.buttonPanel);
        button.setOnClickListener(v -> LOGIN.selectCharacter(character.getCharacter_id(), character.getWorld_id(), new OnSuccess<CharacterSelected>() {
            @Override
            public void onSuccess(CharacterSelected result) {
                LOGIN.completeLogin();
                SecondVillageRepository.getInstance().requestInfo();
                CharacterRepository.getInstance().getInfo(new OnSuccess<CharacterInfo>() {
                    @Override
                    public void onSuccess(CharacterInfo characterInfo) {
                        openVillageActivity(characterInfo.getVillages().get(0).getId());
                    }
                });
            }
        }));
        return convertView;
    }

    private void openVillageActivity(int villageId) {
        Intent intent = new Intent(getContext(), VillageActivity.class);
        intent.putExtra("village", villageId);
        getContext().startActivity(intent);
    }


}
