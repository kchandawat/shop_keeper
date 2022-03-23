package com.marquedo.marquedo.Services;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.darsh.multipleimageselect.activities.AlbumSelectActivity;
import com.darsh.multipleimageselect.helpers.Constants;
import com.darsh.multipleimageselect.models.Image;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.marquedo.marquedo.ProductsNCategories.Product.AboutModelClass;
import com.marquedo.marquedo.ProductsNCategories.imageAdapterDownload;
import com.marquedo.marquedo.ProductsNCategories.preview_product;
import com.marquedo.marquedo.ProductsNCategories.update_product;
import com.marquedo.marquedo.R;

import java.util.ArrayList;
import java.util.List;

public class UpdateService extends AppCompatActivity
{

    private String position;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();;
    private EditText category, name, details, price, discount_price, measure, number_of_hours;
    private Button preview, update;
    private TextInputLayout productCategoryTIL;
    private BottomSheetDialog bottomSheetDialog;
    private BottomSheetDialog addCatBottomSheet;

    private RecyclerView recyclerView;
    private ServiceModelClass serviceModelClass;
    private MaterialButton add_images_button;
    private imageAdapterDownload imageAdapterDownload;
    //private imageAdapter imageAdapter;
    private ActivityResultLauncher<Intent> getResult;


    List<String> Images = new ArrayList<>();
    List<String> newImages = new ArrayList<>();
    List<String> prevImages = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.services_activity_update_service);

        preview = findViewById(R.id.show_preview);
        update = findViewById(R.id.update_service_button);
        //category = findViewById(R.id.prodCategory);
        //productCategoryTIL = findViewById(R.id.product_category_til);
        name = findViewById(R.id.serviceName);
        price = findViewById(R.id.servicePrice);
        number_of_hours = findViewById(R.id.num_of_hours);
        measure = findViewById(R.id.serviceMeasure);
        discount_price = findViewById(R.id.serviceDiscount);
        details = findViewById(R.id.serviceDetails);
        add_images_button = findViewById(R.id.add_service_images);
        recyclerView = findViewById(R.id.update_service_recyclerView);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(null);


        // Upload and update images to firestore
        add_images_button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int count = recyclerView.getAdapter().getItemCount();
                if(count == 6)
                {
                    Toast.makeText(UpdateService.this, "6 Images are already selected. Delete some to choose more", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Intent intent = new Intent(UpdateService.this, AlbumSelectActivity.class);
                    intent.putExtra(Constants.INTENT_EXTRA_LIMIT, 6);
                    getResult.launch(intent);
                }
            }
        });


        getResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result)
            {
                ArrayList<Image> images = result.getData().getParcelableArrayListExtra(Constants.INTENT_EXTRA_IMAGES);
                //Images.clear();
                int count = Images.size();
                for (int i = 0; i < images.size(); i++)
                {
                    Images.add(images.get(i).path);
                    newImages.add(images.get(i).path);
                }
                imageAdapterDownload.notifyItemRangeInserted(count, images.size());
                //imageAdapter = new imageAdapter(Images);
                //recyclerView.setAdapter(imageAdapter);
            }

        });


        Bundle intent = getIntent().getExtras();
        position = intent.get("key").toString();


        db.collection("Store").document("uniquename.TFTVHvZaHOIxjYLnHvwc").collection("services").document(position).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>()
        {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot)
            {
                List<String> Urls = (List<String>) documentSnapshot.get("images");

                prevImages = Urls;

                imageAdapterDownload = new imageAdapterDownload(Urls);
                recyclerView.setAdapter(imageAdapterDownload);

                serviceModelClass = documentSnapshot.toObject(ServiceModelClass.class);
                //List<String> Images = aboutModelClass.getUrls();
                //String Category = documentSnapshot.getString("Category");
                //String Price = String.valueOf(documentSnapshot.get("Price"));
                String Category = serviceModelClass.getCategory();
                String Name = serviceModelClass.getName();
                String Details = serviceModelClass.getDetails();
                String Number_of_hours = String.valueOf(serviceModelClass.getNumber_of_Hours());
                String Measure = serviceModelClass.getMeasure();
                String Price = String.valueOf(serviceModelClass.getPrice());
                String Discount_price = String.valueOf(serviceModelClass.getDiscount_Price());

                name.setText(Name);
                details.setText(Details);
                discount_price.setText(Discount_price);
                price.setText(Price);
                measure.setText(Measure);
                number_of_hours.setText(Number_of_hours);
            }
        });


        preview.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(UpdateService.this, PreviewService.class);
                intent.putExtra("key", position);
                startActivity(intent);

            }
        });

    }

}