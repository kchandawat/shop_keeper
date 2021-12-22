package com.marquedo.marquedo;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.darsh.multipleimageselect.activities.AlbumSelectActivity;
import com.darsh.multipleimageselect.helpers.Constants;
import com.darsh.multipleimageselect.models.Image;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;

import eltos.simpledialogfragment.color.SimpleColorDialog;


public class new_product extends AppCompatActivity
{
    RecyclerView recyclerView;
    MaterialButton add_images_button;
    imageAdapter imageAdapter;
    ArrayList<String> Images = new ArrayList<>();
    ActivityResultLauncher<Intent> getResult;

    private static final int PICK_IMAGES_CODE = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
        MaterialButton button=(MaterialButton) findViewById(R.id.add_new_product_variant_button);
        Button add_product_button=(Button)findViewById(R.id.add_product_button);
        recyclerView = findViewById((R.id.product_images_recyclerView));
        add_images_button = findViewById(R.id.add_product_images);


        imageAdapter = new imageAdapter(Images);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(imageAdapter);


        add_images_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(new_product.this, AlbumSelectActivity.class);
                intent.putExtra(Constants.INTENT_EXTRA_LIMIT, 6);
                getResult.launch(intent);
            }
        });


        getResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        ArrayList<Image> images = result.getData().getParcelableArrayListExtra(Constants.INTENT_EXTRA_IMAGES);
                        Images.clear();
                        for (int i = 0; i < images.size(); i++)
                        {
                            Images.add(images.get(i).path);
                        }
                        imageAdapter.notifyDataSetChanged();
                    }

        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1=getLayoutInflater().inflate(R.layout.activity_varient_card,null);
                BottomSheetDialog bottomSheetDialog= new BottomSheetDialog(view.getContext());
                bottomSheetDialog.setContentView(view1);
                bottomSheetDialog.show();
                ExpandableLayout expandableLayout=(ExpandableLayout) view1.findViewById(R.id.expandable_layout);
                ExpandableLayout expandableLayout1=(ExpandableLayout) view1.findViewById(R.id.expandable_layout_1) ;
                TextView dropdown1=(TextView)  view1.findViewById(R.id.add_size_variant);
                TextView dropdown2=(TextView)view1.findViewById(R.id.add_color_variant) ;
                Button new_color=(Button)view1.findViewById(R.id.add_new_color_variant_button);

                dropdown1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        expandableLayout.toggle();

                    }
                });

                dropdown2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        expandableLayout1.toggle();

                    }
                });

                new_color.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SimpleColorDialog.build()
                                .title("pick_a_color")
                                .colorPreset(Color.RED)
                                .allowCustom(true)
                                .show(new_product.this, "dialogTagColor");
                    }
                });



            }


        });

        add_product_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                View view2=getLayoutInflater().inflate(R.layout.product_added_success,null);
                BottomSheetDialog bottomSheetDialog1= new BottomSheetDialog(v.getContext());
                bottomSheetDialog1.setContentView(view2);
                bottomSheetDialog1.show();
            }
        });

    }


}