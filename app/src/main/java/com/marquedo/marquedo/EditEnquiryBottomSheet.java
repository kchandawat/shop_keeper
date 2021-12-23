package com.marquedo.marquedo;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.marquedo.marquedo.models.Orders_details_overview_model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EditEnquiryBottomSheet extends BottomSheetDialogFragment {

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        //Set the custom view
        View view = LayoutInflater.from(getContext()).inflate(R.layout.edit_enquiry_bottomsheet, null);
        dialog.setContentView(view);

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) view.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(view.getContext(), R.style.CustomAlertDialog);
        bottomSheetDialog.setContentView(R.layout.activity_enquiry_details_overview);
        ExtendedFloatingActionButton close = view.findViewById(R.id.close);


        Window window = bottomSheetDialog.getWindow();
        window.setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
        JSONObject jsonorder1 = new JSONObject();
        try {
            jsonorder1.put("price","30,000");
            jsonorder1.put("ProductName","OnePlus 9 5G (Winter Mist, 12GB RAM, 256GB)");
            jsonorder1.put("quantity","2 X");
            jsonorder1.put("total","60,000");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject jsonorder2 = new JSONObject();
        try {
            jsonorder2.put("price","30,000");
            jsonorder2.put("ProductName","OnePlus 9 5G (Winter Mist, 12GB RAM, 256GB)");
            jsonorder2.put("quantity","1 X");
            jsonorder2.put("total","30,000");

        } catch (JSONException e) {
            e.printStackTrace();
        }


        JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonorder1);
        jsonArray.put(jsonorder2);
        ArrayList<Orders_details_overview_model> newOrders = Orders_details_overview_model.fromJson(jsonArray);
        RecyclerView rvOrders = (RecyclerView) view.findViewById(R.id.EnquiriesRecyclerList);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvOrders.getContext(),
                linearLayoutManager.getOrientation());
        rvOrders.addItemDecoration(dividerItemDecoration);



        RecyclerAdapter adapter = new RecyclerAdapter(newOrders);
        rvOrders.setAdapter(adapter);
        rvOrders.setLayoutManager(linearLayoutManager);





        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.dismiss();
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

    public class RecyclerAdapter extends
            RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        // Provide a direct reference to each of the views within a data item
        // Used to cache the views within the item layout for fast access
        public  class ViewHolder extends RecyclerView.ViewHolder {
            // Your holder should contain a member variable
            // for any view that will be set as you render a row
            public TextView tvProductName ;
            public TextView tvProductPrice ;
            public TextView tvOrderQuantity  ;
            public TextView tvOrderTotal  ;
            public TextView tveditQuantity;

            // We also create a constructor that accepts the entire item row
            // and does the view lookups to find each subview
            public ViewHolder(View itemView) {
                // Stores the itemView in a public final member variable that can be used
                // to access the context from any ViewHolder instance.
                super(itemView);
                tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);
                tvProductPrice = (TextView) itemView.findViewById(R.id.tVProductPrice);
                tvOrderQuantity = (TextView) itemView.findViewById(R.id.tVproductQuantity);
                tvOrderTotal = (TextView) itemView.findViewById(R.id.tVOrderTotal);
                tveditQuantity = itemView.findViewById(R.id.editQuantity);
            }
        }

        @Override
        public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            // Inflate the custom layout
            View ordersView = inflater.inflate(R.layout.edit_order_tile, parent, false);

            // Return a new holder instance
            RecyclerAdapter.ViewHolder viewHolder = new RecyclerAdapter.ViewHolder(ordersView);
            return viewHolder;
        }



        private List<Orders_details_overview_model> orders;

        public RecyclerAdapter(List<Orders_details_overview_model> orders) {
            this.orders = orders;
        }

        // Involves populating data into the item through holder
        @Override
        public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
            // Get the data model based on position
            Orders_details_overview_model order = orders.get(position);

            // Set item views based on your views and data model

            TextView productName = holder.tvProductName;
            productName.setText(order.getProductName());
            TextView productPrice = holder.tvProductPrice;
            productPrice.setText(order.getPrice());
            TextView productQuantity = holder.tvOrderQuantity;
            productQuantity.setText(order.getQuantity());
            TextView total = holder.tvOrderTotal;
            total.setText(order.getTotal());
            TextView editQuantity = holder.tveditQuantity;
            editQuantity.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editQuantityBottomSheet EditQuantityBottomSheet = new editQuantityBottomSheet(order.getQuantity(), order);
                    EditQuantityBottomSheet.show( getFragmentManager(), "TAG");
                }
            });

        }

        // Returns the total count of items in the list
        @Override
        public int getItemCount() {
            return orders.size();
        }
    }
}


