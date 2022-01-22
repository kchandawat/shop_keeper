package com.marquedo.marquedo.SplashScreens;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.marquedo.marquedo.R;

public class congrats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen_letsgetstartedpage);
        Intent progress=new Intent(this, com.marquedo.marquedo.Home.progress.class);
        Button lets_continue=(Button)findViewById(R.id.continue_button);
        lets_continue.setOnClickListener(view -> {
            startActivity(progress);
            finish();
        });
    }
}