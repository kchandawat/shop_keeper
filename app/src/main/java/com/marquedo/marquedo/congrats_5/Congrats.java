package com.marquedo.marquedo.congrats_5;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.marquedo.marquedo.R;
import com.marquedo.marquedo.progress_6.Progress;

public class Congrats extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.congrats_5_congrats);
        Intent progress=new Intent(this, Progress.class);
        Button lets_continue=(Button)findViewById(R.id.lets_get_started_button);
        lets_continue.setOnClickListener(view -> {
            startActivity(progress);
            finish();
        });
    }
}