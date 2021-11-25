package com.example.marquedo;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class preview_product extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_product_preview);
        ImageButton close = findViewById(R.id.close_btn);
        close.setOnClickListener(view -> finish());
    }
}
