package com.example.marquedo.ui.dashboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.marquedo.R;
import com.example.marquedo.ui.dashboard.DashboardViewModel;

public class privacypolicy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.privacypolicypage);
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