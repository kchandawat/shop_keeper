package com.marquedo.marquedo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MaterialButton getStarted= findViewById(R.id.continue_button);
        getStarted.setOnClickListener(view -> StartLogin());
    }

    private void StartLogin() {
        Intent login=new Intent(this,Mobile_Login.class);
        startActivity(login);
    }
}