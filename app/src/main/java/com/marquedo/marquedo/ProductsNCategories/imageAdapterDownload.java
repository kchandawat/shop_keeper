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
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class imageAdapterDownload extends RecyclerView.Adapter<imageAdapterDownload.holder>
{
    List<String> imageUrl;
    //ArrayList<String> imagePath;


    public imageAdapterDownload(List<String> imageUrl)//, ArrayList<String> string)
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
        //Glide.with(holder.imageView.getContext()).load(imageUrl.get(position)).into(holder.imageView);
        //Glide.with(holder.imageView.getContext()).load(imageUrl.get(position)).into(holder.imageView);
        Picasso.get().load(imageUrl.get(position)).into(holder.imageView, new Callback()
        {
            @Override
            public void onSuccess()
            {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e)
            {

            }
        });

          /* Picasso.get().load(imageUrl.get(position)).networkPolicy(NetworkPolicy.OFFLINE).into(holder.imageView, new Callback()
        {
            @Override
            public void onSuccess()
            {
                holder.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e)
            {
                Glide.with(holder.imageView.getContext()).load(imageUrl.get(position)).into(holder.imageView);
            }
        });*/
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
        private ImageButton imageButton;
        private ImageView imageView;
        private imageAdapterDownload imageAdapter;
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
                    imageAdapter.imageUrl.remove(getAdapterPosition());
                    //imageAdapter.imagePath.remove(getAdapterPosition());
                    imageAdapter.notifyItemRemoved(getAdapterPosition());
                }
            });
        }

        public holder linkAdapter(imageAdapterDownload imageAdapter)
        {
            this.imageAdapter = imageAdapter;
            return this;
        }
    }

}
