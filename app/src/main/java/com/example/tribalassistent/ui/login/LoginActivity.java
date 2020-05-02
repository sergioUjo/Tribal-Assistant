package com.example.tribalassistent.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.tribalassistent.R;
import com.example.tribalassistent.data.model.authentication.Player;
import com.example.tribalassistent.ui.character.CharacterActivity;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private LoginViewModel loginViewModel;
    private Context mContext = this;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        Button loginButton = findViewById(R.id.login_button);
        EditText userName = findViewById(R.id.username);
        EditText userPassword = findViewById(R.id.password);
        loginButton.setOnClickListener(v -> loginViewModel.login(userName.getText().toString(), userPassword.getText().toString()));


        loginViewModel.getLoginResult().observe(this, playerResult -> {
            try {
                Player player = playerResult.getData();
                Toast.makeText(mContext, "Welcome " + player.getName(), Toast.LENGTH_SHORT).show();
                openCharacterActivity();
            } catch (NoSuchFieldException e) {
                Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void openCharacterActivity() {
        loginViewModel.systemIdentify();
        Intent intent = new Intent(mContext, CharacterActivity.class);
        startActivity(intent);
    }

}
