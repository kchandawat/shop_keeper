package com.marquedo.marquedo.ui_2_Prod_n_Cat;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.marquedo.marquedo.R;

public class PreviewProduct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_2_prod_n_cat_fragment_product_preview);
        ImageButton close = findViewById(R.id.close_btn);
        close.setOnClickListener(view -> finish());
    }
}
