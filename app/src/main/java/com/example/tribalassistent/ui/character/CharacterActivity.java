package com.example.tribalassistent.ui.character;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.tribalassistent.R;
import com.example.tribalassistent.data.model.authentication.Player;
import com.example.tribalassistent.data.model.character.CharacterInfo;
import com.example.tribalassistent.data.repositories.CharacterRepository;
import com.example.tribalassistent.data.repositories.LoginRepository;
import com.example.tribalassistent.ui.village.VillageActivity;

public class CharacterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);
        ListView listView = findViewById(R.id.character_listview);
        Player user = LoginRepository.getInstance().getUser();
        CharacterListAdapter listAdapter = new CharacterListAdapter(this, R.layout.layout_world_selection, user.getCharacters());
        listView.setAdapter(listAdapter);

        CharacterRepository.getInstance().getCharacterInfo().observe(this, new Observer<CharacterInfo>() {
            @Override
            public void onChanged(CharacterInfo characterInfo) {
                openVillageActivity(characterInfo.getVillages().get(0).getId());
            }
        });
    }

    private void openVillageActivity(int villageId) {
        Intent intent = new Intent(this, VillageActivity.class);
        intent.putExtra("village", villageId);
        this.startActivity(intent);
    }

}
