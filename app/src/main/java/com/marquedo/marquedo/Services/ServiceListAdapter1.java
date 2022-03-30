package com.marquedo.marquedo.Services;

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
import com.marquedo.marquedo.ProductsNCategories.Product.ProductListAdapter1;
import com.marquedo.marquedo.ProductsNCategories.Product.ProductModelClass;
import com.marquedo.marquedo.ProductsNCategories.update_product;
import com.marquedo.marquedo.R;

public class ServiceListAdapter1 extends FirestoreRecyclerAdapter<ServiceModelClass, ServiceListAdapter1.ViewHolder>
{
    public ServiceListAdapter1(@NonNull FirestoreRecyclerOptions<ServiceModelClass> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull ServiceModelClass model)
    {
        String docId = getSnapshots().getSnapshot(position).getId();
        holder.serviceTitle.setText(model.getName());
        holder.servicePrice.setText(String.valueOf(model.getPrice()));
        holder.serviceOffer.setText(String.valueOf(model.getDiscount_Price()));
        Glide.with(holder.serviceImage.getContext()).load(model.getImage_Primary()).into(holder.serviceImage);


        holder.cardView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(holder.cardView.getContext(), UpdateService.class);
                intent.putExtra("key", docId);
                holder.cardView.getContext().startActivity(intent);

            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.service_item, null);
        return new ViewHolder(view);
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView serviceTitle, servicePrice, serviceOffer;
        MaterialButton availability;
        ImageView serviceImage;
        CardView cardView;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            serviceTitle = itemView.findViewById(R.id.service_title);
            servicePrice = itemView.findViewById(R.id.service_price);
            serviceOffer = itemView.findViewById(R.id.service_offer_price);
            availability = itemView.findViewById(R.id.service_availability);
            serviceImage = itemView.findViewById(R.id.service_image);
            cardView = itemView.findViewById(R.id.item_cv);
        }
    }
}

