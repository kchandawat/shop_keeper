package com.marquedo.marquedo.ProductsNCategories.Category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.marquedo.marquedo.R;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {


    ArrayList<CategoriesDataModel> data = new ArrayList<>();

    public CategoryListAdapter(ArrayList<CategoriesDataModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public CategoryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryListAdapter.ViewHolder holder, int position) {
        holder.categoryTitle.setText(data.get(position).title);
        holder.categorySub.setText(data.get(position).sub);
        if (!(data.get(position).availability)) {
            holder.availability.setText("Not Available");
            holder.availability.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.availability_negative, 0);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryTitle, categorySub;
        MaterialButton availability;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTitle = itemView.findViewById(R.id.category_title);
            categorySub = itemView.findViewById(R.id.category_sub);
            availability = itemView.findViewById(R.id.availability_ind);
        }
    }
}
