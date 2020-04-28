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

import com.example.tribalassistent.R;
import com.example.tribalassistent.data.comunication.OnResultListener;
import com.example.tribalassistent.data.comunication.Result;
import com.example.tribalassistent.data.model.authentication.Player;
import com.example.tribalassistent.data.repositories.LoginRepository;
import com.example.tribalassistent.data.repositories.SystemRepository;
import com.example.tribalassistent.ui.character.CharacterActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, OnResultListener<Player> {
    private static final String TAG = "LoginActivity";
    private static final LoginRepository LOGIN = LoginRepository.getInstance();
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
    }

    private void openCharacterActivity() {
        Intent intent = new Intent(mContext, CharacterActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "Login button pressed.");
        LOGIN.setOnLogin(this);
        LOGIN.login(userName.getText().toString(), userPassword.getText().toString());
    }

    @Override
    public void onResult(Result<Player> result) {
        try {
            Player player = result.getData();
            LOGIN.setLoggedInUser(player);
            openCharacterActivity();
        } catch (NoSuchFieldException e) {
            Toast.makeText(mContext, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
