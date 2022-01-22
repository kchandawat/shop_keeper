package com.marquedo.marquedo.SplashScreens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;
import com.marquedo.marquedo.Authentication.Mobile_Login;
import com.marquedo.marquedo.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreens_activity_main);
        MaterialButton getStarted= findViewById(R.id.continue_button);
        getStarted.setOnClickListener(view -> StartLogin());
    }

    private void StartLogin() {
        Intent login=new Intent(this, Mobile_Login.class);
        startActivity(login);
    }
}