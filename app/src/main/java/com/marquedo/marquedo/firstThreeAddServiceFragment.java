package com.marquedo.marquedo;

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
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class firstThreeAddServiceFragment extends Fragment implements CheckboxData
{

    private BottomSheetDialog bottomSheetDialog;
    private TextInputLayout serviceNameTIL;
    private TextInputLayout serviceCategoryTIL;
    private List<String> dreamsList;
    private final List<String> currentSelectedItems = new ArrayList<>();

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

        MaterialButton continuebtn = v.findViewById(R.id.service_continue_button);
        continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), profileSetupAddProductPageActivity.class));
            }
        });

//        setInitialData();

        serviceNameTIL = v.findViewById(R.id.service_name_til);
        serviceCategoryTIL = v.findViewById(R.id.service_category_til);
        MaterialButton signup= v.findViewById(R.id.op_continue_button);
//        Intent congrats=new Intent(this, AddProductNServiceActivity.class);

        bottomSheetDialog = new BottomSheetDialog(v.getContext(), R.style.CustomAlertDialog);
        bottomSheetDialog.setContentView(R.layout.fragment_new_services_category);
        MaterialButton close = bottomSheetDialog.findViewById(R.id.close_sheet);
//        RecyclerView recyclerView = bottomSheetDialog.findViewById(R.id.recyclerview);

        getInitialData();

        //Create adapter
        CategoriesRecyclerViewAdapter categoriesRecyclerViewAdapter = new CategoriesRecyclerViewAdapter(dreamsList, v.getContext(), this);

//        Objects.requireNonNull(recyclerView).setAdapter(categoriesRecyclerViewAdapter);
        Window window = bottomSheetDialog.getWindow();
        window.setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);

        MaterialButton submit = bottomSheetDialog.findViewById(R.id.submit_button);
        Objects.requireNonNull(submit).setOnClickListener(view -> {
            if (!currentSelectedItems.isEmpty()) {
                for (int i = 0; i < currentSelectedItems.size(); i++) {
                    if (i == 0) {
                        String catString = currentSelectedItems.get(i);
                        Objects.requireNonNull(serviceCategoryTIL.getEditText()).setText(catString);
                    } else {
                        String catString = currentSelectedItems.get(i);
                        serviceCategoryTIL.getEditText().setText(catString);
                    }
                }
                bottomSheetDialog.dismiss();
            } else {
                Snack snack = new Snack(v.getContext());
                snack.snackBar(signup, "Please select a category...");
            }
        });

        Objects.requireNonNull(close).setOnClickListener(view -> {
            bottomSheetDialog.dismiss();
            //currentSelectedItems.clear();
        });

        Objects.requireNonNull(serviceCategoryTIL.getEditText()).setOnClickListener(view -> bottomSheetDialog.show());
//        signup.setOnClickListener(view -> {
//            congrats.putExtra(PRODUCT_NAME_KEY, Objects.requireNonNull(productNameTIL.getEditText()).getText().toString());
//            congrats.putExtra(PRODUCT_CATEGORY_KEY, Objects.requireNonNull(productCategoryTIL.getEditText()).getText().toString());
//            startActivity(congrats);
//        });



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

    private void getInitialData() {
        dreamsList = new ArrayList<>();
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(OTP_PREFERENCES, Context.MODE_PRIVATE);
        int size = sharedPreferences.getInt(BUSINESS_CATEGORY_SIZE_KEY, 1);
        for (int i = 0; i < size; i++) {
            String category = sharedPreferences.getString(BUSINESS_CATEGORY_KEY + i, "Loading... Please Try Again!");
            dreamsList.add(category);
        }
    }

    @Override
    public void onCheckboxCheck(String checkboxData) {
        currentSelectedItems.add(checkboxData);
        Log.d(TAG, currentSelectedItems.toString());
    }

    @Override
    public void onCheckboxUnCheck(String checkboxData) {
        currentSelectedItems.remove(checkboxData);
        Log.d(TAG, currentSelectedItems.toString());
    }
}