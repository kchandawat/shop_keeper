package com.example.marquedo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;

import net.cachapa.expandablelayout.ExpandableLayout;

public class new_product extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
        MaterialButton button=(MaterialButton) findViewById(R.id.add_new_product_variant_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1=getLayoutInflater().inflate(R.layout.activity_varient_card,null);
                BottomSheetDialog bottomSheetDialog= new BottomSheetDialog(view.getContext());
                bottomSheetDialog.setContentView(view1);
                bottomSheetDialog.show();
                ExpandableLayout expandableLayout=(ExpandableLayout) view1.findViewById(R.id.expandable_layout);
                TextView dropdown1=(TextView)  view1.findViewById(R.id.add_size_variant);
                dropdown1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        expandableLayout.toggle();

                    }
                });


            }
        });
    }
}