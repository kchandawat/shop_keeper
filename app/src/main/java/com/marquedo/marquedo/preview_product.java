package com.marquedo.marquedo;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.marquedo.marquedo.ProductsNCategories.Product.AboutModelClass;

import java.util.ArrayList;
import java.util.List;

public class preview_product extends AppCompatActivity
{

    private String position;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private AboutModelClass aboutModelClass;
    private TextView name, units, measure, price, offerPrice, details;
    private ImageSlider imageSlider;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productsncategories_fragment_product_preview);

        name = findViewById(R.id.product_title);
        units = findViewById(R.id.product_units);
        measure = findViewById(R.id.product_measure);
        price = findViewById(R.id.product_price);
        offerPrice = findViewById(R.id.product_offer_price);
        details = findViewById(R.id.product_description);



        imageSlider = findViewById(R.id.image_slider);
        final List<SlideModel> displayImages = new ArrayList<>();

        ImageButton close = findViewById(R.id.close_btn);
        close.setOnClickListener(view -> finish());


        Bundle intent = getIntent().getExtras();
        position = intent.get("key").toString();






        db.collection("Store").document("uniquename.TFTVHvZaHOIxjYLnHvwc").collection("products").document(position).collection("about").document("product_id").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>()
        {
            @Override
            public void onSuccess(@NonNull DocumentSnapshot documentSnapshot)
            {
                aboutModelClass = documentSnapshot.toObject(AboutModelClass.class);

                List<String> Urls = (List<String>) documentSnapshot.get("Images");

                displayImages.add(new SlideModel(documentSnapshot.get("Images").toString(), ScaleTypes.FIT));

                //displayImages.add(new SlideModel(Urls.toString(), ScaleTypes.FIT));
                Log.i("check", documentSnapshot.get("Images").toString());

                imageSlider.setImageList(displayImages,ScaleTypes.FIT);
                imageSlider.startSliding(3000);


                String Category = aboutModelClass.getCategory();
                String Name = aboutModelClass.getName();
                String Details = aboutModelClass.getDetails();
                String Number_of_product = String.valueOf(aboutModelClass.getNumber_of_Units());
                String Measure = aboutModelClass.getUnit_Measure();
                String Price = String.valueOf(aboutModelClass.getPrice());
                String Discount_price = String.valueOf(aboutModelClass.getDiscount_Price());


                name.setText(Name);
                details.setText(Details);
                offerPrice.setText(Discount_price);
                price.setText(Price);
                measure.setText(Measure);
                units.setText(Number_of_product);
            }
        });

    }


}
