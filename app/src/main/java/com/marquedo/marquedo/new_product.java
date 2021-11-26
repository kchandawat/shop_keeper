package com.marquedo.marquedo;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.Objects;

public class new_product extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
        MaterialButton button = findViewById(R.id.add_new_product_variant_button);
        Button add_product_button = findViewById(R.id.add_product_button);

        button.setOnClickListener(view -> {
            View view1=getLayoutInflater().inflate(R.layout.activity_varient_card,null);
            BottomSheetDialog bottomSheetDialog= new BottomSheetDialog(view.getContext());
            bottomSheetDialog.setContentView(view1);

            RecyclerView recyclerView = bottomSheetDialog.findViewById(R.id.variant_recyclerview);

            //Create adapter
            VariantRecyclerViewAdapter categoriesRecyclerViewAdapter = new VariantRecyclerViewAdapter(this, this);

            Objects.requireNonNull(recyclerView).setAdapter(categoriesRecyclerViewAdapter);
            Window window = bottomSheetDialog.getWindow();
            window.setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
            ExpandableLayout expandableLayout = view1.findViewById(R.id.expandable_layout);

            TextView dropdown1 =  view1.findViewById(R.id.add_size_variant);

            EditText sizeVariantName = view1.findViewById(R.id.size_variant_name);
            EditText variantPrice = view1.findViewById(R.id.variant_price);
            EditText variantDiscountedPrice = view1.findViewById(R.id.variant_discounted_price);
            MaterialButton new_color = view1.findViewById(R.id.add_new_color_variant_button);
            ExpandableLayout expandableLayout1 = view1.findViewById(R.id.expandable_layout_1);
            TextView dropdown2 = view1.findViewById(R.id.add_color_variant);

            MaterialButton addVariantButton = view1.findViewById(R.id.add_variant_button);
            dropdown1.setOnClickListener(v -> expandableLayout.toggle());
            addVariantButton.setOnClickListener(view2 -> {
                /*if (sizeVariantName.getText().length()==0){
                    return;
                }
                if (variantPrice.getText().length()==0){
                    return;
                }
                if (color==0){
                    return;
                }*/

            });

            bottomSheetDialog.show();
        });

        add_product_button.setOnClickListener(v -> {
            View view2 = getLayoutInflater().inflate(R.layout.product_added_success,null);
            BottomSheetDialog bottomSheetDialog1 = new BottomSheetDialog(v.getContext());
            bottomSheetDialog1.setContentView(view2);
            bottomSheetDialog1.show();
        });
    }
}