package com.marquedo.marquedo.ui.Prod_n_Cat.Category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.marquedo.marquedo.R;
import com.google.android.material.button.MaterialButton;
import com.marquedo.marquedo.ui.Prod_n_Cat.Product.ProductListAdapter;

import java.util.ArrayList;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {


    ArrayList<CategoryModelClass> data = new ArrayList<>();

    public CategoryListAdapter(ArrayList<CategoryModelClass> data)
    {
        this.data = data;
    }

    @NonNull
    @Override
    public CategoryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_item, null);
        return new CategoryListAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CategoryListAdapter.ViewHolder holder, int position) {
        holder.categoryTitle.setText(data.get(position).getName());
        holder.categoryQty.setText(String.valueOf(data.get(position).getNumberofProducts()));
        /*if (!(data.get(position).getNumberofProducts() < 0)) {
           holder.availability.setText("Not Available");
            holder.availability.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.availability_negative, 0);
        }*/
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView categoryTitle, categoryQty;
        MaterialButton availability;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryTitle = itemView.findViewById(R.id.category_title);
            categoryQty = itemView.findViewById(R.id.category_sub);
            availability = itemView.findViewById(R.id.availability_ind);
        }
    }
}
