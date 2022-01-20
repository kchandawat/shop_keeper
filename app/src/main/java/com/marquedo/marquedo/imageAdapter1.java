package com.marquedo.marquedo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.marquedo.marquedo.ProductsNCategories.Product.AboutModelClass;

public class imageAdapter1 extends FirestoreRecyclerAdapter<AboutModelClass, imageAdapter1.holder>
{


    public imageAdapter1(@NonNull FirestoreRecyclerOptions<AboutModelClass> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull holder holder, int position, @NonNull AboutModelClass model)
    {
       // Glide.with(holder.imageView.getContext()).load(model.getUrls()).into(holder.imageView);
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_image_item,parent,false);
        return new holder(view);
    }

    class holder extends RecyclerView.ViewHolder {
        ImageButton imageButton;
        ImageView imageView;
        private imageAdapter1 imageAdapter;


        public holder(@NonNull View itemView) {
            super(itemView);
            imageButton = itemView.findViewById(R.id.imageBtn);
            imageView = itemView.findViewById(R.id.imageView);
            /*imageButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    imageAdapter1.imagePath.remove(getAdapterPosition());
                    imageAdapter1.notifyItemRemoved(getAdapterPosition());
                }
            });*/
        }

        /*public holder linkAdapter(imageAdapter1 imageAdapter)
        {
            this.imageAdapter = imageAdapter;
            return this;
        }*/




    }

}
