package com.example.tribalassistent.ui.character;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tribalassistent.R;
import com.example.tribalassistent.data.model.authentication.Player;
import com.example.tribalassistent.data.repositories.LoginRepository;

public class CharacterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character);
        ListView listView = findViewById(R.id.character_listview);
        Player user = LoginRepository.getInstance().getUser();
        CharacterListAdapter listAdapter = new CharacterListAdapter(this, R.layout.layout_world_selection, user.getCharacters());
        listView.setAdapter(listAdapter);
    }



}
