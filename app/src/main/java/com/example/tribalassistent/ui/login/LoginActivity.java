package com.example.tribalassistent.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tribalassistent.R;
import com.example.tribalassistent.data.comunication.Result;
import com.example.tribalassistent.data.model.authentication.Player;
import com.example.tribalassistent.data.repositories.SystemRepository;
import com.example.tribalassistent.ui.character.CharacterActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";
    private LoginViewModel loginViewModel;
    private Context mContext = this;
    private Button loginButton;
    private EditText userName;
    private EditText userPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        SystemRepository.getInstance();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton = findViewById(R.id.login_button);
        userName = findViewById(R.id.username);
        userPassword = findViewById(R.id.password);
        loginButton.setOnClickListener(this);
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        loginViewModel.getLoginResult().observe(this, new Observer<Result<Player>>() {
            @Override
            public void onChanged(Result<Player> playerResult) {
                try {
                    Player player = playerResult.getData();
                    loginViewModel.setLoggedIn(player);
                    openCharacterActivity();
                } catch (NoSuchFieldException e) {
                    Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void openCharacterActivity() {
        Intent intent = new Intent(mContext, CharacterActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "Login button pressed.");
        loginViewModel.login(userName.getText().toString(), userPassword.getText().toString());
    }
}
