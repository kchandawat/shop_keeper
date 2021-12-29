package com.marquedo.marquedo.ui.Services;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.marquedo.marquedo.R;

import java.util.ArrayList;

public class ServiceListAdapter extends RecyclerView.Adapter<ServiceListAdapter.ViewHolder> {

    ArrayList<ServiceDataModel> data;

    public ServiceListAdapter(ArrayList<ServiceDataModel> data) {
        this.data = data;
    }


    @NonNull
    @Override
    public ServiceListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.service_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ServiceListAdapter.ViewHolder holder, int position) {
        holder.serviceTitle.setText(data.get(position).title);
        if (!(data.get(position).availability)) {
            holder.availability.setText("Not Available");
            holder.availability.setCompoundDrawablesWithIntrinsicBounds(0, 0,
                    R.drawable.availability_negative, 0);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView serviceTitle;
        MaterialButton availability;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            serviceTitle = itemView.findViewById(R.id.service_title);
            availability = itemView.findViewById(R.id.availability_ind);
        }
    }
}
