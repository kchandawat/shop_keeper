package com.marquedo.marquedo.progress_6;

import static com.marquedo.marquedo.helpers.OurConstants.BUSINESS_CATEGORY_KEY;
import static com.marquedo.marquedo.helpers.OurConstants.BUSINESS_CATEGORY_SIZE_KEY;
import static com.marquedo.marquedo.helpers.OurConstants.OTP_PREFERENCES;
import static com.marquedo.marquedo.helpers.OurConstants.PRODUCT_CATEGORY_KEY;
import static com.marquedo.marquedo.helpers.OurConstants.PRODUCT_NAME_KEY;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.marquedo.marquedo.R;
import com.marquedo.marquedo.business_details_4.CategoriesRecyclerViewAdapter;
import com.marquedo.marquedo.business_details_4.CheckboxData;
import com.marquedo.marquedo.helpers.Snack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddNewProductNC extends AppCompatActivity implements CheckboxData {

    private static final String TAG = "OnlyPN";
    private BottomSheetDialog bottomSheetDialog;
    private TextInputLayout productNameTIL;
    private TextInputLayout productCategoryTIL;
    private List<String> dreamsList;
    private final List<String> currentSelectedItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_6_add_new_product_nc);

        productNameTIL = findViewById(R.id.product_name_til);
        productCategoryTIL = findViewById(R.id.product_category_til);
        MaterialButton signup= findViewById(R.id.op_continue_button);
        Intent congrats=new Intent(this, NewProductDetails.class);

        bottomSheetDialog = new BottomSheetDialog(this, R.style.CustomAlertDialog);
        bottomSheetDialog.setContentView(R.layout.activity_business_category);
        ExtendedFloatingActionButton close = bottomSheetDialog.findViewById(R.id.close);
        RecyclerView recyclerView = bottomSheetDialog.findViewById(R.id.recyclerview);

        getInitialData();

        //Create adapter
        CategoriesRecyclerViewAdapter categoriesRecyclerViewAdapter = new CategoriesRecyclerViewAdapter(dreamsList, this, this);

        Objects.requireNonNull(recyclerView).setAdapter(categoriesRecyclerViewAdapter);
        Window window = bottomSheetDialog.getWindow();
        window.setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);

        MaterialButton submit = bottomSheetDialog.findViewById(R.id.submit);
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
                Snack snack = new Snack(this);
                snack.snackBar(signup, "Please select a category...");
            }
        });

        Objects.requireNonNull(close).setOnClickListener(view -> {
            bottomSheetDialog.dismiss();
            //currentSelectedItems.clear();
        });

        Objects.requireNonNull(productCategoryTIL.getEditText()).setOnClickListener(view -> bottomSheetDialog.show());
        signup.setOnClickListener(view -> {
            congrats.putExtra(PRODUCT_NAME_KEY, Objects.requireNonNull(productNameTIL.getEditText()).getText().toString());
            congrats.putExtra(PRODUCT_CATEGORY_KEY, Objects.requireNonNull(productCategoryTIL.getEditText()).getText().toString());
            startActivity(congrats);
        });
    }

    private void getInitialData() {
        dreamsList = new ArrayList<>();
        SharedPreferences sharedPreferences = getSharedPreferences(OTP_PREFERENCES, MODE_PRIVATE);
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