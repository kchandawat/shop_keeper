package com.marquedo.marquedo.datab;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SizeVarAdapter extends RecyclerView.Adapter {

    ArrayList<Variant> variantArrayList;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    public SizeVarAdapter(ArrayList<Variant> variantArrayList) {
        this.variantArrayList = variantArrayList;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    public class VariantViewHolder extends RecyclerView.ViewHolder {

        public VariantViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
