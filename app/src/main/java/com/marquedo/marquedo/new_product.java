package com.marquedo.marquedo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;

import net.cachapa.expandablelayout.ExpandableLayout;

import eltos.simpledialogfragment.color.SimpleColorDialog;

public class new_product extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
        MaterialButton button=(MaterialButton) findViewById(R.id.add_new_product_variant_button);
        Button add_product_button=(Button)findViewById(R.id.continue_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1=getLayoutInflater().inflate(R.layout.activity_varient_card,null);
                BottomSheetDialog bottomSheetDialog= new BottomSheetDialog(view.getContext());
                bottomSheetDialog.setContentView(view1);
                bottomSheetDialog.show();
                ExpandableLayout expandableLayout=(ExpandableLayout) view1.findViewById(R.id.expandable_layout);
                ExpandableLayout expandableLayout1=(ExpandableLayout) view1.findViewById(R.id.expandable_layout_1) ;
                TextView dropdown1=(TextView)  view1.findViewById(R.id.add_size_variant);
                TextView dropdown2=(TextView)view1.findViewById(R.id.add_color_variant) ;
                Button new_color=(Button)view1.findViewById(R.id.add_new_color_variant_button);

                dropdown1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        expandableLayout.toggle();

                    }
                });

                dropdown2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        expandableLayout1.toggle();

                    }
                });

                new_color.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SimpleColorDialog.build()
                                .title("pick_a_color")
                                .colorPreset(Color.RED)
                                .allowCustom(true)
                                .show(new_product.this, "dialogTagColor");
                    }
                });



            }


        });

        add_product_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View view2=getLayoutInflater().inflate(R.layout.product_added_success,null);
                BottomSheetDialog bottomSheetDialog1= new BottomSheetDialog(v.getContext());
                bottomSheetDialog1.setContentView(view2);
                bottomSheetDialog1.show();
            }
        });
    }
}