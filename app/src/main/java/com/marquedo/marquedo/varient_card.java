package com.marquedo.marquedo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import net.cachapa.expandablelayout.ExpandableLayout;

public class varient_card extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("onclick", "clicking ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_varient_card);

        ExpandableLayout expandableLayout = findViewById(R.id.expandable_layout);
        TextView dropdown1 = findViewById(R.id.add_size_variant);
        dropdown1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandableLayout.toggle();

            }
        });
    }
}