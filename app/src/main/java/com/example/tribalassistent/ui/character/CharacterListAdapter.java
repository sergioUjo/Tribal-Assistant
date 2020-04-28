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
import com.example.tribalassistent.data.comunication.OnResultListener;
import com.example.tribalassistent.data.comunication.Result;
import com.example.tribalassistent.data.model.authentication.Character;
import com.example.tribalassistent.data.model.authentication.CharacterSelected;
import com.example.tribalassistent.data.model.character.CharacterInfo;
import com.example.tribalassistent.data.model.village.VillageGameBatch;
import com.example.tribalassistent.data.repositories.CharacterRepository;
import com.example.tribalassistent.data.repositories.LoginRepository;
import com.example.tribalassistent.data.repositories.VillageRepository;
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
        Button button = convertView.findViewById(R.id.buttonPanel);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LOGIN.setOnCharacterSelected(new OnResultListener<CharacterSelected>() {
                    @Override
                    public void onResult(Result<CharacterSelected> result) {
                        CharacterRepository.getInstance().getCharacterInfo(new OnResultListener<CharacterInfo>() {
                            @Override
                            public void onResult(Result<CharacterInfo> characterInfoResult) {
                                try {
                                    openVillageActivity(characterInfoResult.getData().getVillages().get(0).getId());
                                } catch (NoSuchFieldException e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                    }
                });
                LOGIN.select(character.getCharacter_id(), character.getWorld_id());

            }
        });
        textView.setText(character.getWorld_name());
        return convertView;
    }

    private void openVillageActivity(int villageId) {
        Intent intent = new Intent(getContext(), VillageActivity.class);
        intent.putExtra("village", villageId);
        VillageRepository.getInstance().getVillageData(new OnResultListener<VillageGameBatch>() {
            @Override
            public void onResult(Result<VillageGameBatch> result) {
            }
        });
        getContext().startActivity(intent);
    }
}
