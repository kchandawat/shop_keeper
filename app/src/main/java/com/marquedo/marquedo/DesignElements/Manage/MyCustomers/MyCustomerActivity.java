package com.marquedo.marquedo.DesignElements.Manage.MyCustomers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.marquedo.marquedo.R;

public class MyCustomerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.designelements_manage_mycustomer_activity_my_customer);

        RecyclerView customers = findViewById(R.id.rvMyCustomers);
        ImageButton backBtn = findViewById(R.id.btnBackArrow);
        RecyclerAdapter adapter = new RecyclerAdapter();
        customers.setAdapter(adapter);
        customers.setLayoutManager(new LinearLayoutManager(this));

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    public class RecyclerAdapter extends
            RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        // Provide a direct reference to each of the views within a data item
        // Used to cache the views within the item layout for fast access
        public class ViewHolder extends RecyclerView.ViewHolder {
            // Your holder should contain a member variable
            // for any view that will be set as you render a row
            public TextView tvProductName;
            public TextView tvProductPrice;
            public TextView tvOrderQuantity;
            public TextView tvOrderTotal;

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
        public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            // Inflate the custom layout
            View myCustomersView = inflater.inflate(R.layout.designelements_manage_mycustomer_tile, parent, false);

            // Return a new holder instance
            RecyclerAdapter.ViewHolder viewHolder = new RecyclerAdapter.ViewHolder(myCustomersView);
            return viewHolder;
        }

//        private List<myCustomersModel> orders;

//        public RecyclerAdapter(List<Orders_details_overview_model> orders) {
//            this.orders = orders;
//        }

        // Involves populating data into the item through holder
        @Override
        public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
            // Get the data model based on position
//            Orders_details_overview_model order = orders.get(position);

            // Set item views based on your views and data model

//            TextView productName = holder.tvProductName;
//            productName.setText(order.getProductName());
//            TextView productPrice = holder.tvProductPrice;
//            productPrice.setText(order.getPrice());
//            TextView productQuantity = holder.tvOrderQuantity;
//            productQuantity.setText(order.getQuantity());
//            TextView total = holder.tvOrderTotal;
//            total.setText(order.getTotal());

        }

        // Returns the total count of items in the list
        @Override
        public int getItemCount() {
            return 3;
        }
    }

}