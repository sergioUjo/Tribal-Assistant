package com.example.tribalassistent.ui.login;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tribalassistent.client.AsyncCallback;
import com.example.tribalassistent.data.model.authentication.Player;
import com.example.tribalassistent.data.model.system.Error;
import com.example.tribalassistent.data.repositories.LoginRepository;
import com.example.tribalassistent.data.repositories.SystemRepository;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<String> loginResult = new MutableLiveData<>();
    private LoginRepository loginRepository = LoginRepository.getInstance();

    public MutableLiveData<String> getLoginResult() {
        return loginResult;
    }

    public void login(String username, String password) {
        loginRepository.login(username, password, new AsyncCallback<Player>() {
            @Override
            public void onFailure(Error error) {
                loginResult.postValue(error.getMessage());
            }

            @Override
            public void onSuccess(Player result) {
                loginResult.postValue("Welcome" + result.getName() + "!");
            }
        });
    }

    public void systemIdentify() {
        SystemRepository.getInstance().identify(null);
    }
}
