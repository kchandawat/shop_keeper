package com.marquedo.marquedo.DesignElements.Manage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.marquedo.marquedo.R;
import com.marquedo.marquedo.DesignElements.DashboardViewModel;

public class privacypolicy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_privacypolicypage);
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