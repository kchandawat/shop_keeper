package com.marquedo.marquedo;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class imageAdapter extends RecyclerView.Adapter<imageAdapter.holder>
{

    Context c;
    //ArrayList<model_image> Model_images;
    ArrayList<Uri> uri;

    public imageAdapter(Context c, ArrayList<Uri> uri)//ArrayList<model_image> model_images)
    {
        this.c = c;
       // this.Model_images = model_images;
        this.uri = uri;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(c).inflate(R.layout.activity_image_item,parent,false);
        return new holder(v) ;
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position)
    {
        Uri uri1 = uri.get(position);
        //model_image mi = Model_images.get(position);
        //Glide.with(c).load(uri1).into(holder.imageView);
        //Picasso.with(c).load(mi.getUri()).placeholder()
    }


    @Override
    public int getItemCount()
    {
        return 6;
    }

    class holder extends RecyclerView.ViewHolder
    {
        ImageButton imageButton;
        ImageView imageView;

        public holder(@NonNull View itemView)
        {
            super(itemView);
            imageButton=itemView.findViewById(R.id.imageBtn);
            imageView=itemView.findViewById(R.id.imageView);
        }
    }
}
