package com.marquedo.marquedo;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.darsh.multipleimageselect.activities.AlbumSelectActivity;
import com.darsh.multipleimageselect.helpers.Constants;
import com.darsh.multipleimageselect.models.Image;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class profileSetupAddProductPageActivity extends AppCompatActivity
{

    private RecyclerView recyclerView;
    private imageAdapter imageAdapter;

    private Button AddImages, AddVarient, AddProduct;
    private EditText ProdPrice, ProdDiscount, ProdDetails, ProdMeasure, NumberofProd;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();

    private ArrayList<String> Images = new ArrayList<>();
    private ActivityResultLauncher<Intent> getResult;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup_product_page);

        recyclerView = findViewById(R.id.prod_images_recyclerView);
        AddImages = findViewById(R.id.add_product_images);
        AddVarient = findViewById(R.id.add_new_product_variant_button);
        AddProduct = findViewById(R.id.add_product_button);
        ProdPrice = findViewById(R.id.prod_price);
        ProdDiscount = findViewById(R.id.prod_discount);
        ProdDetails = findViewById(R.id.prod_details);
        ProdMeasure = findViewById(R.id.prod_measure);
        NumberofProd = findViewById(R.id.number_of_prod);



        imageAdapter = new imageAdapter(Images);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(imageAdapter);


        AddImages.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), AlbumSelectActivity.class);
                intent.putExtra(Constants.INTENT_EXTRA_LIMIT, 6);
                getResult.launch(intent);
            }
        });


        getResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result)
            {
                ArrayList<Image> images = result.getData().getParcelableArrayListExtra(Constants.INTENT_EXTRA_IMAGES);
                Images.clear();
                for (int i = 0; i < images.size(); i++)
                {
                    Images.add(images.get(i).path);
                }
                imageAdapter.notifyDataSetChanged();
            }

        });


        AddProduct.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String Measure = ProdMeasure.getText().toString();
                String Number_of_Product = NumberofProd.getText().toString();
                String Price = ProdPrice.getText().toString();
                String Discount_Price = ProdDiscount.getText().toString();
                String Details = ProdDetails.getText().toString();

            }
        });



    }



}