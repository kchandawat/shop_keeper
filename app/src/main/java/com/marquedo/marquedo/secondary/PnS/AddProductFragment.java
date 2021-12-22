package com.marquedo.marquedo.secondary.PnS;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.darsh.multipleimageselect.activities.AlbumSelectActivity;
import com.darsh.multipleimageselect.helpers.Constants;
import com.darsh.multipleimageselect.models.Image;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.marquedo.marquedo.R;
import com.marquedo.marquedo.imageAdapter;
import com.marquedo.marquedo.progress;
import com.marquedo.marquedo.ui.Prod_n_Cat.Product.ProductsFragment;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;

import eltos.simpledialogfragment.color.SimpleColorDialog;

public class AddProductFragment extends Fragment
{
    RecyclerView recyclerView;
    MaterialButton add_images_button;
    com.marquedo.marquedo.imageAdapter imageAdapter;
    ArrayList<String> Images = new ArrayList<>();
    ActivityResultLauncher<Intent> getResult;

    private static final int PICK_IMAGES_CODE = 0;

    public AddProductFragment()
    {

    }


    public static AddProductFragment newInstance(String param1, String param2) {
        AddProductFragment fragment = new AddProductFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_product, container, false);


        MaterialButton button = (MaterialButton) view.findViewById(R.id.add_new_product_variant_button);
        Button add_product_button = (Button)view.findViewById(R.id.add_product_button);
        recyclerView = view.findViewById((R.id.product_images_recyclerView));
        add_images_button = view.findViewById(R.id.add_product_images);


        imageAdapter = new imageAdapter(Images);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(imageAdapter);


        add_images_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity().getApplicationContext(), AlbumSelectActivity.class);
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
                                .show(AddProductFragment.this, "dialogTagColor");
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

                bottomSheetDialog1.findViewById(R.id.submit_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottomSheetDialog1.dismiss();
                        startActivity(new Intent(v.getContext(), progress.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                    }
                });
            }
        });

        return view;
    }

}