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


public class firstThreeAddServiceFragment extends Fragment
{
    public firstThreeAddServiceFragment()
    {

    }

    public static firstThreeAddServiceFragment newInstance(String param1, String param2) {
        firstThreeAddServiceFragment fragment = new firstThreeAddServiceFragment();
        return fragment;
    }

    private EditText ServiceName, ServiceCategory;
    private Button Continue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_first_three_add_service, container, false);
        ServiceName = v.findViewById(R.id.service_name);
        ServiceCategory = v.findViewById(R.id.service_category);
        Continue = v.findViewById(R.id.service_continue_button);

        Continue.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
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