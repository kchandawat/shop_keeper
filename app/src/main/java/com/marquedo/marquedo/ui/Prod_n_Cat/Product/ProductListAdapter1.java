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
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.button.MaterialButton;
import com.marquedo.marquedo.R;
import com.marquedo.marquedo.update_product;

public class ProductListAdapter1 extends FirestoreRecyclerAdapter<ProductModelClass, ProductListAdapter1.ViewHolder>
{

    public ProductListAdapter1(@NonNull FirestoreRecyclerOptions<ProductModelClass> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull ProductModelClass model)
    {
        String docId = getSnapshots().getSnapshot(position).getId();
        holder.productTitle.setText(model.getName());
        holder.productMeasure.setText(model.getUnit_Measure());
        holder.productUnits.setText(String.valueOf(model.getNumber_of_Units()));
        holder.productPrice.setText(String.valueOf(model.getPrice()));
        holder.productOffer.setText(String.valueOf(model.getDiscount_Price()));
        Glide.with(holder.prodImage.getContext()).load(model.getImage_Primary()).into(holder.prodImage);
        holder.cardView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(holder.cardView.getContext(), update_product.class);
                intent.putExtra("key", docId);
                holder.cardView.getContext().startActivity(intent);

            }
        });

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.products_item, null);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView productTitle, productUnits, productMeasure, productPrice, productOffer;
        MaterialButton availability;
        ImageView prodImage;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productTitle = itemView.findViewById(R.id.product_title);
            productUnits = itemView.findViewById(R.id.product_units);
            productMeasure = itemView.findViewById(R.id.product_measure);
            productPrice = itemView.findViewById(R.id.product_price);
            productOffer = itemView.findViewById(R.id.product_offer_price);
            availability = itemView.findViewById(R.id.prod_availability);
            prodImage = itemView.findViewById(R.id.prod_image);
            cardView = itemView.findViewById(R.id.item_cv);

            /*cardView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(itemView.getContext(), update_product.class);
                    intent.putExtra("key", getRef(getAdapterPosition()).getKey());
                    itemView.getContext().startActivity(intent);
                }
            });*/

            /*cardView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(itemView.getContext(), update_product.class);
                    intent.putExtra("key", getAdapterPosition());
                    itemView.getContext().startActivity(intent);

                }
            });*/


        }

    }
}