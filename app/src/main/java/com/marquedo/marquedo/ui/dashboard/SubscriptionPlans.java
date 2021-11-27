package com.marquedo.marquedo.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.marquedo.marquedo.R;

public class SubscriptionPlans extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subscription_page);
        ImageButton back = (ImageButton) this.findViewById(R.id.imageButton);
        Intent dashboard = new Intent(this, DashboardViewModel.class);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}