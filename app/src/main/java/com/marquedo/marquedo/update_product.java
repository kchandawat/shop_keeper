package com.marquedo.marquedo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.darsh.multipleimageselect.activities.AlbumSelectActivity;
import com.darsh.multipleimageselect.helpers.Constants;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.marquedo.marquedo.ui.Prod_n_Cat.Product.AboutModelClass;
import com.marquedo.marquedo.ui.Prod_n_Cat.Product.ProductListAdapter1;
import com.marquedo.marquedo.ui.Prod_n_Cat.Product.ProductModelClass;

import java.util.ArrayList;
import java.util.List;

public class update_product extends AppCompatActivity
{

    private String position;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private EditText category, name, details, price, discount_price, measure, number_of_prod;


    private RecyclerView recyclerView;
    private AboutModelClass aboutModelClass;
    private MaterialButton add_images_button;
    private imageAdapter2 imageAdapter2;
    //private ArrayList<String> Images = new ArrayList<>();
    private ActivityResultLauncher<Intent> getResult;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        Intent preview = new Intent(this, preview_product.class);
        Button preview_prod = findViewById(R.id.show_preview);
        preview_prod.setOnClickListener(view ->
        {
            startActivity(preview);
        });


        category = findViewById(R.id.prodCategory);
        name = findViewById(R.id.productName);
        price = findViewById(R.id.prodPrice);
        number_of_prod = findViewById(R.id.Num_of_prod);
        measure = findViewById(R.id.prodMeasure);
        discount_price = findViewById(R.id.prodDiscount);
        details = findViewById(R.id.prodDetails);
        add_images_button = findViewById(R.id.addImage_updateProd);
        recyclerView = findViewById(R.id.update_prod_recyclerView);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(null);


        //String id = db.collection("Store").document().getId();

        // Upload and update images to firestore
        add_images_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int count = recyclerView.getAdapter().getItemCount();
                if(count == 6)
                {
                    Toast.makeText(update_product.this, "6 Images are already selected. Delete some to choose more", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(update_product.this, AlbumSelectActivity.class);
                    intent.putExtra(Constants.INTENT_EXTRA_LIMIT, 6);
                    getResult.launch(intent);
                }

            }
        });

        getResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result)
            {

            }

        });


        //Get the position of the item clicked in the recycler view
        Bundle intent = getIntent().getExtras();
        position = intent.get("key").toString();


        //Getting data from firestore and displaying it on the fields
        db.collection("Store").document("uniquename.TFTVHvZaHOIxjYLnHvwc").collection("products").document(position).collection("about").document("product_id").get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(@NonNull DocumentSnapshot documentSnapshot)
            {
                aboutModelClass = documentSnapshot.toObject(AboutModelClass.class);
                //List<String> imageurl = aboutModelClass.getUrls();

                ArrayList<String> Urls = (ArrayList<String>) documentSnapshot.get("Images");
                Log.i("showlist", Urls.toString());
                imageAdapter2 = new imageAdapter2(Urls);
                recyclerView.setAdapter(imageAdapter2);


                /*FirestoreRecyclerOptions<AboutModelClass> options = new FirestoreRecyclerOptions.Builder<AboutModelClass>()
                        .setQuery(query, AboutModelClass.class)
                        .build();

                imageAdapter1 = new imageAdapter1(options);
                recyclerView.setAdapter(imageAdapter1);*/



                //List<String> Images = aboutModelClass.getUrls();
                //String Category = documentSnapshot.getString("Category");
                // String Price = String.valueOf(documentSnapshot.get("Price"));
                String Category = aboutModelClass.getCategory();
                String Name = aboutModelClass.getName();
                String Details = aboutModelClass.getDetails();
                String Number_of_product = String.valueOf(aboutModelClass.getNumber_of_Units());
                String Measure = aboutModelClass.getUnit_Measure();
                String Price = String.valueOf(aboutModelClass.getPrice());
                String Discount_price = String.valueOf(aboutModelClass.getDiscount_Price());



                //aboutModelClass = documentSnapshot.get("Category", AboutModelClass.class);
                //category.setText(aboutModelClass.getCategory());
                category.setText(Category);
                name.setText(Name);
                details.setText(Details);
                discount_price.setText(Discount_price);
                price.setText(Price);
                measure.setText(Measure);
                number_of_prod.setText(Number_of_product);

            }
        });
        //CollectionReference collectionReference = db.collection("Store");
        //db.collectionGroup("about");
        //Log.i("trial", db.collectionGroup("about").orderBy(pos).get().toString());*/
    }
}
