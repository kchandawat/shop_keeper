package com.marquedo.marquedo.ui_3_orders_enquiries;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.marquedo.marquedo.R;
import com.marquedo.marquedo.models.OrdersDetailsOverviewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrderDetailsOverview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_3_orders_activity_order_detail_overview1);
        Button RejectButton = findViewById(R.id.btnRejectOrder);
        ImageButton backButton = findViewById(R.id.btnBackArrow);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        RejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view2 = getLayoutInflater().inflate(R.layout.order_reject_confirmation, null);
                Button yesReject = view2.findViewById(R.id.btnYesRejectOrderButton);
                Button close = view2.findViewById(R.id.close_sheet);
                BottomSheetDialog bottomSheetDialog1 = new BottomSheetDialog(view.getContext());
                bottomSheetDialog1.setContentView(view2);
                bottomSheetDialog1.show();
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                            bottomSheetDialog1.cancel();
                    }
                });
//                Button yesButton = findViewById(R.id.btnYesRejectOrderButton);

//                yesButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                    }
//                });
            }
        });



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
        ArrayList<OrdersDetailsOverviewModel> newOrders = OrdersDetailsOverviewModel.fromJson(jsonArray);
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.OrderRecyclerList);


        RecyclerAdapter adapter = new RecyclerAdapter(newOrders);
        rvContacts.setAdapter(adapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));


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
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            // Inflate the custom layout
            View ordersView = inflater.inflate(R.layout.ui_3_orders_tile, parent, false);

            // Return a new holder instance
            ViewHolder viewHolder = new ViewHolder(ordersView);
            return viewHolder;
        }
        private List<OrdersDetailsOverviewModel> orders;

        public RecyclerAdapter(List<OrdersDetailsOverviewModel> orders) {
            this.orders = orders;
        }

        // Involves populating data into the item through holder
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            // Get the data model based on position
            OrdersDetailsOverviewModel order = orders.get(position);

            // Set item views based on your views and data model

            TextView productName = holder.tvProductName;
            productName.setText(order.getProductName());
            TextView productPrice = holder.tvProductPrice;
            productPrice.setText(order.getPrice());
            TextView productQuantity = holder.tvOrderQuantity;
            productQuantity.setText(order.getQuantity());
            TextView total = holder.tvOrderTotal;
            total.setText(order.getTotal());

        }

        // Returns the total count of items in the list
        @Override
        public int getItemCount() {
            return orders.size();
        }
    }
}
