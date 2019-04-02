package com.manoj.mvvm_demo.ui.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.manoj.mvvm_demo.R;
import com.manoj.mvvm_demo.databinding.ActivityLoginBinding;
import com.manoj.mvvm_demo.model.LoginUser;

import java.util.Objects;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

// Tutorial referred : https://github.com/umangburman/MVVM-DataBinding-With-LiveData-Login

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        binding.setLifecycleOwner(this);

        binding.setLoginViewModel(loginViewModel);

        loginViewModel.getUserData().observe(this, new Observer<LoginUser>() {
            @Override
            public void onChanged(LoginUser loginUser) {

                if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).getEmail())) {
                    binding.edtEmail.setError("Enter Email");
                    binding.edtEmail.requestFocus();
                } else if (!loginUser.isEmailValid()) {
                    binding.edtEmail.setError("Enter valid Email");
                    binding.edtEmail.requestFocus();
                } else if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).getPassword())) {
                    binding.edtPass.setError("Enter Password");
                    binding.edtPass.requestFocus();
                } else {
                    binding.edtEmail.setText(loginUser.getEmail());
                    binding.edtPass.setText(loginUser.getPassword());
                    Toast.makeText(LoginActivity.this, "Login successfull", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
