package com.marquedo.marquedo.Home;

import static android.content.ContentValues.TAG;
import static com.marquedo.marquedo.OurConstants.BUSINESS_CATEGORY_KEY;
import static com.marquedo.marquedo.OurConstants.BUSINESS_CATEGORY_SIZE_KEY;
import static com.marquedo.marquedo.OurConstants.OTP_PREFERENCES;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.marquedo.marquedo.CategoriesRecyclerViewAdapter;
import com.marquedo.marquedo.CheckboxData;
import com.marquedo.marquedo.R;
import com.marquedo.marquedo.Snack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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
        View v = inflater.inflate(R.layout.home_fragment_first_three_add_product, container, false);
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