package com.abhi.moviemania.views;


import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.abhi.moviemania.R;
import com.abhi.moviemania.databinding.ActivityLoginBinding;
import com.abhi.moviemania.models.User;
import com.abhi.moviemania.viewmodels.LoginViewModel;


public class LoginActivity extends AppCompatActivity {

    Button login_button;
    EditText email_editText,password_editText;

    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        LoginViewModel loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding.setViewModel(loginViewModel);
        binding.setLifecycleOwner(this);
        init();

        loginViewModel.getUser().observe(this, new Observer() {
            @Override
            public void onChanged(@Nullable Object o) {


                User user = (User) o;

                if (user != null) {
                    if (user.isPasswordLengthGreaterThan5() && user.isEmailValid()) {
                        login_button.setVisibility(View.VISIBLE);
                    } else {
                        login_button.setVisibility(View.INVISIBLE);
                    }

                    if (user.getFlag()) {
                        Intent i = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                }
            }
        });
    }

    private void init() {
        login_button = findViewById(R.id.login_button);
        email_editText = findViewById(R.id.email_editText);
        password_editText = findViewById(R.id.password_editText);
    }
}
