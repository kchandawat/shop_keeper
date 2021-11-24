package com.example.marquedo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.marquedo.ui.dashboard.DashboardViewModel;

public class logoutAction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.logout_action);
        Button yes = (Button) this.findViewById(R.id.Yeslogout_button);
        Button no = (Button) this.findViewById(R.id.Nologout_button);
        Button close = (Button) this.findViewById(R.id.close_sheet);

        Intent noLogout = new Intent(this, DashboardViewModel.class);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(noLogout);
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(noLogout);
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}