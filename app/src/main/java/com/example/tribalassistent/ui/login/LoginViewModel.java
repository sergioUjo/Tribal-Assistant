package com.example.tribalassistent.ui.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tribalassistent.data.comunication.request.Result;
import com.example.tribalassistent.data.model.authentication.Player;
import com.example.tribalassistent.data.repositories.LoginRepository;
import com.example.tribalassistent.data.repositories.SystemRepository;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<Result<Player>> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository = LoginRepository.getInstance();

    public MutableLiveData<Result<Player>> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        loginRepository.login(username, password, result -> loginResult.postValue(result));
    }

    public void systemIdentify() {
        SystemRepository.getInstance().systemIdentify();
    }
}
