package com.marquedo.marquedo.ProductsNCategories;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.marquedo.marquedo.R;

import java.util.List;

public class imageAdapter2 extends RecyclerView.Adapter<imageAdapter2.holder>
{
    List<String> imageUrl;
    //ArrayList<String> imagePath;

    public imageAdapter2(List<String> imageUrl)//, ArrayList<String> string)
    {
        this.imageUrl = imageUrl;
        //this.imagePath = string;
    }


    /*public imageAdapter2(ArrayList<String> string)
    {
        this.imagePath = string;
    }*/

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
        //String path = imagePath.get(position);
        //Glide.with(holder.imageView.getContext()).load(new File(path)).into(holder.imageView);
        Glide.with(holder.imageView.getContext()).load(imageUrl.get(position)).into(holder.imageView);
    }


    @Override
    public int getItemCount()
    {
        return imageUrl.size();
       /* int limit = 6;
        return Math.min(0, limit);*/

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
                    //imageAdapter.imagePath.remove(getAdapterPosition());
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
