package com.marquedo.marquedo;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;


public class firstThreeAddProductFragment extends Fragment
{

    public firstThreeAddProductFragment() {
        // Required empty public constructor
    }

    public static firstThreeAddProductFragment newInstance(String param1, String param2)
    {
        firstThreeAddProductFragment fragment = new firstThreeAddProductFragment();
        return fragment;
    }


    private EditText ProdName, ProdCategory;
    private Button Continue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_first_three_add_product, container, false);
        ProdName = v.findViewById(R.id.prod_name);
        ProdCategory = v.findViewById(R.id.prod_category);
        Continue = v.findViewById(R.id.prod_continue_button);

        Continue.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String name = ProdName.getText().toString();
                String category = ProdName.getText().toString();

                Intent intent = new Intent(getContext(), profileSetupAddProductPageActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("category", category);
                startActivity(intent);
            }
        });
        return v;
    }
}