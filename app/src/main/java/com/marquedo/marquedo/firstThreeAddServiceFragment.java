package com.marquedo.marquedo;
;
import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;



public class firstThreeAddServiceFragment extends Fragment {
    public firstThreeAddServiceFragment() {

    }


    private EditText ServiceName, ServiceCategory;
    private Button Continue;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_first_three_add_service, container, false);

        ServiceName = v.findViewById(R.id.service_name);
        ServiceCategory = v.findViewById(R.id.service_category);
        Continue = v.findViewById(R.id.service_continue_button);


        ServiceCategory.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                View view1=getLayoutInflater().inflate(R.layout.fragment_new_product_category,null);
                BottomSheetDialog bottomSheetDialog= new BottomSheetDialog(getContext());
                bottomSheetDialog.setContentView(view1);
                bottomSheetDialog.show();
            }
        });

        Continue.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                String name = ServiceName.getText().toString();
                String category = ServiceCategory.getText().toString();

                Intent intent = new Intent(getContext(), profileSetupAddServicePageActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("category", category);
                startActivity(intent);
            }
        });
        return v;
    }
}


