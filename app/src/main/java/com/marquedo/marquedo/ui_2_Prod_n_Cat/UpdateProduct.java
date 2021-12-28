package com.marquedo.marquedo.ui_2_Prod_n_Cat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.marquedo.marquedo.R;

public class UpdateProduct extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_2_prod_n_cat_activity_update_product);
        Intent preview = new Intent(this, PreviewProduct.class);
        Button preview_prod = findViewById(R.id.show_preview);
        preview_prod.setOnClickListener(view -> {
            startActivity(preview);
        });
    }
}
