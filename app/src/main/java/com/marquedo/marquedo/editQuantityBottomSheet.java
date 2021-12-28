package com.marquedo.marquedo;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.marquedo.marquedo.models.Orders_details_overview_model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class editQuantityBottomSheet extends BottomSheetDialogFragment {

    public  int existingQuantity;
    private ImageButton increase;
    private ImageButton decrease;
    private EditText quantityET;
    private MaterialButton submitBtn;
    private Orders_details_overview_model model;
    editQuantityBottomSheet(String quantity, Orders_details_overview_model model){
        this.existingQuantity = Integer.parseInt(quantity.split(" ")[0]);
        this.model = model;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        //Set the custom view
        View view = LayoutInflater.from(getContext()).inflate(R.layout.edit_quantity, null);
        dialog.setContentView(view);

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) view.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(view.getContext(), R.style.CustomAlertDialog);
        bottomSheetDialog.setContentView(R.layout.activity_order_detail_overview1);
        ExtendedFloatingActionButton close = view.findViewById(R.id.close);
        submitBtn = view.findViewById(R.id.submit);

        increase = view.findViewById(R.id.increase);
        decrease = view.findViewById(R.id.decrease);
        quantityET = view.findViewById(R.id.quantityET);

        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increaseQuantity(view);
            }
        });

        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decreaseQuantity(view);
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(model.getQuantity());
                model.setQuantity(String.valueOf(existingQuantity + " X"));
                bottomSheetDialog.dismiss();
                dialog.dismiss();
            }
        });

        Window window = bottomSheetDialog.getWindow();
        window.setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);




        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
                dialog.dismiss();
            }
        });

        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    String state = "";

                    switch (newState) {
                        case BottomSheetBehavior.STATE_DRAGGING: {
                            state = "DRAGGING";
                            break;
                        }
                        case BottomSheetBehavior.STATE_SETTLING: {
                            state = "SETTLING";
                            break;
                        }
                        case BottomSheetBehavior.STATE_EXPANDED: {
                            state = "EXPANDED";
                            break;
                        }
                        case BottomSheetBehavior.STATE_COLLAPSED: {
                            state = "COLLAPSED";
                            break;
                        }
                        case BottomSheetBehavior.STATE_HIDDEN: {
                            dismiss();
                            state = "HIDDEN";
                            break;
                        }
                    }

                    Toast.makeText(getContext(), "Bottom Sheet State Changed to: " + state, Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                }
            });
        }
    }

    public void increaseQuantity(View view) {
        existingQuantity = existingQuantity + 1;
        display(existingQuantity);

    }public void decreaseQuantity(View view) {
        existingQuantity = existingQuantity - 1;
        display(existingQuantity);
    }

    private void display(int number) {
        quantityET.setText(String.valueOf(existingQuantity));
    }

}
