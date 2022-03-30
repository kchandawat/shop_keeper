package com.marquedo.marquedo.ProductsNCategories;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.marquedo.marquedo.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class imageAdapterUpload extends RecyclerView.Adapter<imageAdapterUpload.holder>
{
    ArrayList<String> imagePath;

    public imageAdapterUpload(ArrayList<String> string)
    {
        this.imagePath = string;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_activity_image_item,parent,false);
        return new holder(v).linkAdapter(this) ;
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position)
    {

        String path = imagePath.get(position);
        Glide.with(holder.imageView.getContext()).load(new File(path)).into(holder.imageView);
        holder.progressBar.setVisibility(View.INVISIBLE);
        //holder.progressBar.setVisibility(View.GONE);
        //Picasso.get().load(new File(path)).into(holder.imageView);
    }


    @Override
    public int getItemCount()
    {
        return imagePath.size();
        /*int limit = 6;
        return Math.min(uri.size(), limit);*/

    }

    class holder extends RecyclerView.ViewHolder
    {
        private ImageButton imageButton;
        private ImageView imageView;
        private imageAdapterUpload imageAdapterUpload;
        private ProgressBar progressBar;

        public holder(@NonNull View itemView)
        {
            super(itemView);
            imageButton=itemView.findViewById(R.id.imageBtn);
            imageView=itemView.findViewById(R.id.imageView);
            progressBar=itemView.findViewById(R.id.image_progress);
            imageButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    imageAdapterUpload.imagePath.remove(getAdapterPosition());
                    imageAdapterUpload.notifyItemRemoved(getAdapterPosition());
                }
            });
        }

        public holder linkAdapter(imageAdapterUpload imageAdapterUpload)
        {
            this.imageAdapterUpload = imageAdapterUpload;
            return this;
        }


    }

}
