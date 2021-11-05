package com.example.marquedo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomsheet.BottomSheetDialog;

public class Only_Product_Name extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_only_product_name);

        EditText business = findViewById(R.id.edittext2);
        Button signup = findViewById(R.id.button);
        Intent congrats = new Intent(this, new_product.class);
        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = getLayoutInflater().inflate(R.layout.fragment_new_product_category, null);
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(view.getContext());
                bottomSheetDialog.setContentView(view1);
                bottomSheetDialog.show();

                view1.findViewById(R.id.close_sheet).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottomSheetDialog.dismiss();
                    }
                });

                view1.findViewById(R.id.add_new_product_category_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        View view2 = getLayoutInflater().inflate(R.layout.fragment_category_sheet, null);
                        bottomSheetDialog.setContentView(view2);

                        view2.findViewById(R.id.close_sheet).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                bottomSheetDialog.setContentView(view1);
                            }
                        });
                    }
                });

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(congrats);


            }
        });
    }
}