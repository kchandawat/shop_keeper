package com.marquedo.marquedo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class imageAdapter2 extends RecyclerView.Adapter<imageAdapter2.holder>
{
    ArrayList<String> imageUrl;
    //ArrayList<String> imagePath;

    public imageAdapter2(ArrayList<String> imageUrl)
    {
        this.imageUrl = imageUrl;
    }


    /*public imageAdapter2(ArrayList<String> string)
    {
        this.imagePath = string;
    }*/

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_image_item,parent,false);
        return new holder(v).linkAdapter(this) ;
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position)
    {
        //String path = imagePath.get(position);
        //Glide.with(holder.imageView.getContext()).load(new File(path)).into(holder.imageView);
        Glide.with(holder.imageView.getContext()).load(imageUrl).into(holder.imageView);
    }


    @Override
    public int getItemCount()
    {
        return imageUrl.size();
        /*int limit = 6;
        return Math.min(uri.size(), limit);*/

    }

    class holder extends RecyclerView.ViewHolder
    {
        ImageButton imageButton;
        ImageView imageView;
        private imageAdapter2 imageAdapter;

        public holder(@NonNull View itemView)
        {
            super(itemView);
            imageButton=itemView.findViewById(R.id.imageBtn);
            imageView=itemView.findViewById(R.id.imageView);
            imageButton.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    imageAdapter.imageUrl.remove(getAdapterPosition());
                    imageAdapter.notifyItemRemoved(getAdapterPosition());
                }
            });
        }

        public holder linkAdapter(imageAdapter2 imageAdapter)
        {
            this.imageAdapter = imageAdapter;
            return this;
        }
    }

}
