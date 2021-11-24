package com.marquedo.marquedo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class congrats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrats);
        Intent progress=new Intent(this, progress.class);
        Button lets_continue=(Button)findViewById(R.id.continue_button);
        lets_continue.setOnClickListener(view -> {
            startActivity(progress);
            finish();
        });
    }
}