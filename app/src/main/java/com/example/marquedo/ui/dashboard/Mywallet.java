package com.example.marquedo.ui.dashboard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.marquedo.R;
import com.example.marquedo.ui.notifications.NotificationsFragment;

public class Mywallet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.walletpage);
        ImageButton back = (ImageButton) this.findViewById(R.id.imageButton);
        Intent dashboard = new Intent(this, DashboardViewModel.class);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.RecyclerWallet);
        RecyclerAdapter adapter=new RecyclerAdapter();
        rvContacts.setAdapter(adapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));

    }

    public class RecyclerAdapter extends
            RecyclerView.Adapter <RecyclerAdapter.ViewHolder> {

        // Provide a direct reference to each of the views within a data item
        // Used to cache the views within the item layout for fast access
        public  class ViewHolder extends RecyclerView.ViewHolder {
            // Your holder should contain a member variable
            // for any view that will be set as you render a row


            // We also create a constructor that accepts the entire item row
            // and does the view lookups to find each subview
            public ViewHolder(View itemView) {
                // Stores the itemView in a public final member variable that can be used
                // to access the context from any ViewHolder instance.
                super(itemView);

            }
        }

        @Override
        public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            // Inflate the custom layout
            View ordersView = inflater.inflate(R.layout.wallet_tile, parent, false);

            // Return a new holder instance
           RecyclerAdapter.ViewHolder viewHolder = new RecyclerAdapter.ViewHolder(ordersView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        }

        @Override
        public int getItemCount() {
            return 4;
        }



        // Involves populating data into the item through holder

    }
}
