package com.marquedo.marquedo.main_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.button.MaterialButton;
import com.marquedo.marquedo.auth_3.MobileNumber;
import com.marquedo.marquedo.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_2_main);
        MaterialButton getStarted= findViewById(R.id.main_lets_get_started_button);
        getStarted.setOnClickListener(view -> StartLogin());
    }

    private void StartLogin() {
        Intent login=new Intent(this, MobileNumber.class);
        startActivity(login);
    }
}