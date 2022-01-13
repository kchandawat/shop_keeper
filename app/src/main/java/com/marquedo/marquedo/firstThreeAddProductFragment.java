package com.marquedo.marquedo;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;
import static com.marquedo.marquedo.OurConstants.BUSINESS_CATEGORY_KEY;
import static com.marquedo.marquedo.OurConstants.BUSINESS_CATEGORY_SIZE_KEY;
import static com.marquedo.marquedo.OurConstants.OTP_PREFERENCES;
import static com.marquedo.marquedo.OurConstants.PRODUCT_CATEGORY_KEY;
import static com.marquedo.marquedo.OurConstants.PRODUCT_NAME_KEY;

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

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.marquedo.marquedo.secondary.PnS.AddProductNServiceActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link firstThreeAddProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class firstThreeAddProductFragment extends Fragment implements  CheckboxData{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
//    private static final String TAG = "OnlyPN";
    private BottomSheetDialog bottomSheetDialog;
    private BottomSheetDialog addCatBottomSheet;
    private TextInputLayout productNameTIL;
    private TextInputLayout productCategoryTIL;
    private List<String> dreamsList;
    private final List<String> currentSelectedItems = new ArrayList<>();

    public firstThreeAddProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment firstThreeAddProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static firstThreeAddProductFragment newInstance(String param1, String param2) {
        firstThreeAddProductFragment fragment = new firstThreeAddProductFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_first_three_add_product, container, false);
        MaterialButton continuebtn = v.findViewById(R.id.prod_continue_button);
        continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), profileSetupAddProductPageActivity.class));
            }
        });

//        setInitialData();

        productNameTIL = v.findViewById(R.id.product_name_til);
        productCategoryTIL = v.findViewById(R.id.product_category_til);
        MaterialButton signup= v.findViewById(R.id.op_continue_button);
//        Intent congrats=new Intent(this, AddProductNServiceActivity.class);

        bottomSheetDialog = new BottomSheetDialog(v.getContext(), R.style.CustomAlertDialog);
        bottomSheetDialog.setContentView(R.layout.fragment_new_product_category);
        MaterialButton close = bottomSheetDialog.findViewById(R.id.close_sheet);
        MaterialButton addCategory = bottomSheetDialog.findViewById(R.id.add_new_product_category_button);
//        RecyclerView recyclerView = bottomSheetDialog.findViewById(R.id.recyclerview);
        addCatBottomSheet = new BottomSheetDialog(v.getContext(), R.style.CustomAlertDialog);
        addCatBottomSheet.setContentView(R.layout.fragment_add_category);
        MaterialButton close2 = addCatBottomSheet.findViewById(R.id.close_sheet);


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
                        Objects.requireNonNull(productCategoryTIL.getEditText()).setText(catString);
                    } else {
                        String catString = currentSelectedItems.get(i);
                        productCategoryTIL.getEditText().setText(catString);
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

        Objects.requireNonNull(productCategoryTIL.getEditText()).setOnClickListener(view -> bottomSheetDialog.show());
//        signup.setOnClickListener(view -> {
//            congrats.putExtra(PRODUCT_NAME_KEY, Objects.requireNonNull(productNameTIL.getEditText()).getText().toString());
//            congrats.putExtra(PRODUCT_CATEGORY_KEY, Objects.requireNonNull(productCategoryTIL.getEditText()).getText().toString());
//            startActivity(congrats);
//        });

        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCatBottomSheet.show();
            }
        });

        close2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCatBottomSheet.dismiss();
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