package com.marquedo.marquedo;

import static com.marquedo.marquedo.InternetCheck.getConnectionType;
import static com.marquedo.marquedo.InternetCheck.getConnectivityStatusString;
import static com.marquedo.marquedo.OurConstants.BUSINESS_CATEGORY_KEY;
import static com.marquedo.marquedo.OurConstants.OTP_FCMID_KEY;
import static com.marquedo.marquedo.OurConstants.OTP_PREFERENCES;
import static com.marquedo.marquedo.OurConstants.SHOP_NAME_KEY;
import static com.marquedo.marquedo.OurConstants.TYPE_NOT_CONNECTED;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Business_Detail extends AppCompatActivity implements CheckboxData {

    private static final String TAG = "BusD";
    List<String> dreamsList = new ArrayList<>(); // List of models
    private TextInputEditText businessNameET;
    private TextInputLayout businessCategoryTIL;
    private TextInputEditText businessCategoryET;
    private final List<String> currentSelectedItems = new ArrayList<>();
    private FirebaseFirestore db;
    private SharedPreferences sharedPreferences;
    private ImageButton businessBackButton;
    private MaterialButton signup;

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String status = getConnectivityStatusString(context);
            internetCheck.setSnackbarMessage(status, signup);
        }
    };
    private final InternetCheck internetCheck = new InternetCheck(true, broadcastReceiver, this);

    private final View.OnClickListener allClickListenerHandlingCL = this::allClickListenerHandling;
    private BottomSheetDialog bottomSheetDialog;

    private void allClickListenerHandling(View view) {
        if (view.getId() == R.id.signup_button){
            int conn = getConnectionType(this);
            if (conn == TYPE_NOT_CONNECTED) {
                internetCheck.snackBar(signup, "Please connect to internet to continue!");
                return;
            }
            signup();
        } else if (view.getId() == R.id.business_category_et){
            bottomSheetDialog.show();
        } else if (view.getId() == R.id.business_category_til){
            bottomSheetDialog.show();
        } else if (view.getId() == R.id.business_back_button){
            backPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_detail);

        initializations();
        operations();
        buttononclicksthis();
    }

    private void initializations() {
        businessNameET = findViewById(R.id.business_name_et);
        businessCategoryTIL = findViewById(R.id.business_category_til);
        businessCategoryET = findViewById(R.id.business_category_et);
        db = FirebaseFirestore.getInstance();
        sharedPreferences = getSharedPreferences(OTP_PREFERENCES, Context.MODE_PRIVATE);
        businessBackButton = findViewById(R.id.business_back_button);
        signup= findViewById(R.id.signup_button);
    }

    private void operations() {
        setInitialData();
        bottomSheetDialog = new BottomSheetDialog(this, R.style.CustomAlertDialog);
        bottomSheetDialog.setContentView(R.layout.activity_business_category);
        ExtendedFloatingActionButton close = bottomSheetDialog.findViewById(R.id.close);
        RecyclerView recyclerView = bottomSheetDialog.findViewById(R.id.recyclerview);

        //Create adapter
        CategoriesRecyclerViewAdapter categoriesRecyclerViewAdapter = new CategoriesRecyclerViewAdapter(dreamsList, this, this);

        Objects.requireNonNull(recyclerView).setAdapter(categoriesRecyclerViewAdapter);
        Window window = bottomSheetDialog.getWindow();
        window.setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);

        MaterialButton submit = bottomSheetDialog.findViewById(R.id.submit);
        Objects.requireNonNull(submit).setOnClickListener(view -> {
            String businessCategoriesString;
            if (!currentSelectedItems.isEmpty()) {
                for (int i = 0; i < currentSelectedItems.size(); i++) {
                    if (i == 0)
                        Objects.requireNonNull(businessCategoryTIL.getEditText()).setText(currentSelectedItems.get(i));
                    else{
                        businessCategoriesString = businessCategoryTIL.getEditText().getText().toString();
                        String catString = businessCategoriesString + ", " + currentSelectedItems.get(i);
                        businessCategoryTIL.getEditText().setText(catString);
                    }
                }
                bottomSheetDialog.dismiss();
            } else {
                Toast.makeText(this, "Please select a category...", Toast.LENGTH_LONG).show();
            }
        });

        Objects.requireNonNull(close).setOnClickListener(view -> {
            bottomSheetDialog.dismiss();
            currentSelectedItems.clear();
        });
    }

    private void buttononclicksthis() {
        businessCategoryTIL.setOnClickListener(allClickListenerHandlingCL);
        businessCategoryET.setOnClickListener(allClickListenerHandlingCL);
        signup.setOnClickListener(allClickListenerHandlingCL);
        businessBackButton.setOnClickListener(allClickListenerHandlingCL);
    }

    private void signup() {
        if (Objects.requireNonNull(businessNameET.getText()).length() == 0) {
            Toast.makeText(this, "Please enter shop name", Toast.LENGTH_LONG).show();
            return;
        }
        String businessCategoriesString1 = Objects.requireNonNull(businessCategoryTIL.getEditText()).getText().toString();
        if (businessCategoriesString1.isEmpty()){
            Toast.makeText(this, "Please select categories", Toast.LENGTH_SHORT).show();
            return;
        }

        String uid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
        Map<String, Object> store = new HashMap<>();
        store.put(SHOP_NAME_KEY, businessNameET.getText().toString());
        store.put(BUSINESS_CATEGORY_KEY, currentSelectedItems);
        if (!uid.isEmpty()) {
            db.collection("Store").document(uid).update(store)
                    .addOnCompleteListener(task -> {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(SHOP_NAME_KEY, businessNameET.getText().toString());
                        for (int i = 0; i < currentSelectedItems.size(); i++) {
                            editor.putString(OTP_FCMID_KEY + i, currentSelectedItems.get(i));
                        }
                        editor.apply();
                        Intent congrats=new Intent(this, congrats.class);
                        congrats.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(congrats);
                    })
                    .addOnFailureListener(e -> Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show());
        }
    }

    private void setInitialData() {
        dreamsList.addAll(Collections.singleton("Kirana"));
        dreamsList.addAll(Collections.singleton("Dairy"));
        dreamsList.addAll(Collections.singleton("Footwear"));
        dreamsList.addAll(Collections.singleton("Food"));
        dreamsList.addAll(Collections.singleton("Cosmetics"));
        dreamsList.addAll(Collections.singleton("Cake"));
        dreamsList.addAll(Collections.singleton("Stationary"));
        dreamsList.addAll(Collections.singleton("Books"));
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

    private void backPressed() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Please check!")
                .setMessage("Do you want to logout?")
                .setPositiveButton("Yes", (dialogInterface, i) -> {
                    getSharedPreferences(OTP_PREFERENCES, MODE_PRIVATE).edit().clear().apply();
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(this, MainActivity.class)
                            .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                })
                .setNegativeButton("No", (dialogInterface, i) -> dialogInterface.cancel())
                .create()
                .show();
    }
}