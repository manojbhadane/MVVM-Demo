package com.manoj.mvvm_demo.ui.login;

import android.view.View;

import com.manoj.mvvm_demo.model.LoginUser;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<String> Emails = new MutableLiveData<>();
    public MutableLiveData<String> passwords = new MutableLiveData<>();

    public MutableLiveData<LoginUser> userMutableLiveData;

    public MutableLiveData<LoginUser> getUserData() {
        if (userMutableLiveData == null)
            userMutableLiveData = new MutableLiveData<>();
        return userMutableLiveData;

    }

    public void onClick(View view) {
        LoginUser user = new LoginUser(Emails.getValue(), passwords.getValue());
        userMutableLiveData.setValue(user);
    }
}
