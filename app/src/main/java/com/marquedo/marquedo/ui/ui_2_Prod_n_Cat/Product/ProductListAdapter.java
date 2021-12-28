package com.marquedo.marquedo.ui.ui_2_Prod_n_Cat.Product;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.marquedo.marquedo.R;

import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    private OnItemClickListener listener = null;

    ArrayList<ProductsDataModel> data = new ArrayList<>();

    public ProductListAdapter(ArrayList<ProductsDataModel> data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    public ProductListAdapter(ArrayList<ProductsDataModel> data) {
        this.data = data;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.ViewHolder holder, int position) {

        holder.productTitle.setText(data.get(position).title);
        holder.productSub.setText(data.get(position).sub);
        holder.productMRP.setText(String.valueOf(data.get(position).mrp));
        holder.productPrice.setText(String.valueOf(data.get(position).price));
        //binding onClickListener
        holder.bind(data.get(position), listener);
    }


    @NonNull
    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ui_ui_2_products_item, null));
    }

    public interface OnItemClickListener {
        void onItemClick(ProductsDataModel item);
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

        public void bind(ProductsDataModel productsDataModel, OnItemClickListener listener) {
            itemView.setOnClickListener(view -> listener.onItemClick(productsDataModel));
        }
    }
}
