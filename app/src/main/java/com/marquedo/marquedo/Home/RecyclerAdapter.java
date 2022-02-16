package com.marquedo.marquedo.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.checkbox.MaterialCheckBox;
import com.marquedo.marquedo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class RecyclerAdapter extends
            RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        // Provide a direct reference to each of the views within a data item
        // Used to cache the views within the item layout for fast access
        public  class ViewHolder extends RecyclerView.ViewHolder {
            // Your holder should contain a member variable
            // for any view that will be set as you render a row
            public MaterialCheckBox checkBox;
            // We also create a constructor that accepts the entire item row
            // and does the view lookups to find each subview
            public ViewHolder(View itemView) {
                // Stores the itemView in a public final member variable that can be used
                // to access the context from any ViewHolder instance.
                super(itemView);
                checkBox = (MaterialCheckBox) itemView.findViewById(R.id.material_check_box) ;

            }
        }

        @Override
        public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            // Inflate the custom layout
            View ordersView = inflater.inflate(R.layout.home_new_category_tile, parent, false);

            // Return a new holder instance
            RecyclerAdapter.ViewHolder viewHolder = new RecyclerAdapter.ViewHolder(ordersView);
            return viewHolder;
        }
        private List<String> categoriesList;

        public RecyclerAdapter(Set<String> category) {
            categoriesList = new ArrayList<>(category);
        }

        // Involves populating data into the item through holder
        @Override
        public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
            // Get the data model based on position
            String category = categoriesList.get(position);

            // Set item views based on your views and data model
            MaterialCheckBox checkBoxText = holder.checkBox;
            checkBoxText.setText(category);


        }

        // Returns the total count of items in the list
        @Override
        public int getItemCount() {
            return categoriesList.size();
        }
    }

