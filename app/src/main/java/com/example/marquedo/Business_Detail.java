package com.example.marquedo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;

public class Business_Detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_detail);
        TextInputEditText business = findViewById(R.id.business_input);
        Button signup = findViewById(R.id.button);
        Intent congrats = new Intent(this, congrats.class);
        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = getLayoutInflater().inflate(R.layout.activity_business_category, null);
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(view.getContext());
                bottomSheetDialog.setContentView(view1);
                bottomSheetDialog.show();
                bottomSheetDialog.findViewById(R.id.close_sheet).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottomSheetDialog.dismiss();
                    }
                });

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                congrats.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(congrats);


            }
        });
    }
}