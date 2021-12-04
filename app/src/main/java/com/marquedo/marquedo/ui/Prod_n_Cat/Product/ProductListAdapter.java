package com.marquedo.marquedo.ui.Prod_n_Cat.Product;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.marquedo.marquedo.R;
import com.marquedo.marquedo.update_product;


import java.io.File;
import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder>
{

    private OnItemClickListener listener = null;

    ArrayList<ProductModelClass> data;

    public ProductListAdapter(ArrayList<ProductModelClass> data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewTyp) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_item, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.ViewHolder holder, int position)
    {
        holder.productTitle.setText(data.get(position).getName());
        holder.productMeasure.setText(data.get(position).getMeasure());
        holder.productQty.setText(String.valueOf(data.get(position).getNumberofProducts()));
        holder.productPrice.setText(String.valueOf(data.get(position).getPrice()));
        holder.productOffer.setText(String.valueOf(data.get(position).getDiscountPrice()));
        Glide.with(holder.prodImage.getContext()).load(data.get(position).getImageurl1()).into(holder.prodImage);
       /* holder.cardView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String str_pos = String.valueOf(position) ;
                Intent intent = new Intent(holder.cardView.getContext(), update_product.class);
                intent.putExtra("key",str_pos);
                holder.cardView.getContext().startActivity(intent);

            }
        });*/
        //binding onClickListener
        //holder.bind(data.get(position),listener);
    }


    public interface OnItemClickListener
    {
        void onItemClick(ProductModelClass item);
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }

    //TODO: Implement Availability Factor
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView productTitle, productQty, productPrice, productOffer, productMeasure;
        MaterialButton availability;
        ImageView prodImage;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productTitle = itemView.findViewById(R.id.product_title);
            productQty = itemView.findViewById(R.id.product_units);
            productMeasure = itemView.findViewById(R.id.product_measure);
            productPrice = itemView.findViewById(R.id.product_price);
            productOffer = itemView.findViewById(R.id.product_offer_price);
            availability = itemView.findViewById(R.id.availability_prod);
            prodImage = itemView.findViewById(R.id.item_image);
            cardView = itemView.findViewById(R.id.item_cv);

            /*cardView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(itemView.getContext(), update_product.class);
                    intent.putExtra("key", getAdapterPosition(getAdapterPosition()).getKey());
                    intent.putExtra("parent", "");
                    itemView.getContext().startActivity(intent);

                }
            });*/


        }

        public void bind(ProductModelClass productmodelclass, OnItemClickListener listener)
        {
            itemView.setOnClickListener(view -> listener.onItemClick(productmodelclass));
        }

    }
}
