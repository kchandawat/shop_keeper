package com.marquedo.marquedo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class update_product extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);
        Intent preview = new Intent(this, preview_product.class);
        Button preview_prod = findViewById(R.id.show_preview);
        preview_prod.setOnClickListener(view -> {
            startActivity(preview);
        });
    }
}
