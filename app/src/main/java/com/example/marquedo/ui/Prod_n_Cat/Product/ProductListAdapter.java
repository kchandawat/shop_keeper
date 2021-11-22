package com.example.marquedo.ui.Prod_n_Cat.Product;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marquedo.R;

import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {


    ArrayList<ProductsDataModel> data = new ArrayList<>();

    public ProductListAdapter(ArrayList<ProductsDataModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.products_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.ViewHolder holder, int position) {

        holder.productTitle.setText(data.get(position).title);
        holder.productSub.setText(data.get(position).sub);
        holder.productMRP.setText(String.valueOf(data.get(position).mrp));
        holder.productPrice.setText(String.valueOf(data.get(position).price));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    //TODO: Implement Availability Factor
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView productTitle, productSub, productMRP, productPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productTitle = itemView.findViewById(R.id.product_title);
            productSub = itemView.findViewById(R.id.product_sub);
            productMRP = itemView.findViewById(R.id.product_price);
            productPrice = itemView.findViewById(R.id.product_offer_price);
        }
    }
}
