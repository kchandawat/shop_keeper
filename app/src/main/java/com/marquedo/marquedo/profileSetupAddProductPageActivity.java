package com.marquedo.marquedo;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.darsh.multipleimageselect.activities.AlbumSelectActivity;
import com.darsh.multipleimageselect.helpers.Constants;
import com.darsh.multipleimageselect.models.Image;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.marquedo.marquedo.AddProductVariant.ColourCodes;
import com.marquedo.marquedo.AddProductVariant.DataExtraction;
import com.marquedo.marquedo.AddProductVariant.GetColourVariants;
import com.marquedo.marquedo.AddProductVariant.GetVariants;
import com.marquedo.marquedo.AddProductVariant.ProductVariantRecyclerViewAdapter;
import com.marquedo.marquedo.AddProductVariant.TheData;
import com.marquedo.marquedo.AddProductVariant.VariantData;
import com.marquedo.marquedo.datab.Variant;
import com.marquedo.marquedo.databinding.Progress6ProductVariantRecyclerviewBinding;
import com.marquedo.marquedo.secondary.PnS.ServiceModelClass;
import com.marquedo.marquedo.ui.Prod_n_Cat.Product.AboutModelClass;
import com.marquedo.marquedo.ui.Prod_n_Cat.Product.ProductModelClass;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class profileSetupAddProductPageActivity extends AppCompatActivity {

    private String ProductName, ProductCategory;

    private RecyclerView recyclerView;
    private imageAdapter imageAdapter;

    private Button AddImages, AddVarient, AddProduct;
    private EditText ProdPrice, ProdDiscount, ProdDetails, ProdMeasure, NumberofProd;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();

    private ArrayList<String> Images = new ArrayList<>();
    private ActivityResultLauncher<Intent> getResult;
    private List<String> imageUrlList = new ArrayList<>();

    int counter;

    public profileSetupAddProductPageActivity() {
    }



    //MAAZ START
    ProductVariantRecyclerViewAdapter productVariantRecyclerViewAdapter;
    private List<VariantData> myList;
    private DataExtraction variantData;
    private List<ColourCodes> colourList;
    private RecyclerView variantRecyclerView;
    private int colourSize;
    private GetVariants getVariants;
    private GetColourVariants getColourVariants;
    private int variantSize;
    private ArrayList<Variant> prodList = new ArrayList<>();
    private Variant variant;
    private Progress6ProductVariantRecyclerviewBinding binding;
    //MAAZ END



    @Override
    protected void onCreate(Bundle savedInstanceState) {
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


        Bundle intent = getIntent().getExtras();
        ProductName = intent.get("name").toString();
        ProductCategory = intent.get("category").toString();


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
                String Number_of_Units = NumberofProd.getText().toString();
                String Price = ProdPrice.getText().toString();
                String Discount_Price = ProdDiscount.getText().toString();
                String Details = ProdDetails.getText().toString();

                ProductModelClass productModelClass = new ProductModelClass(null, ProductName, Measure, Integer.parseInt(Discount_Price)
                        , Integer.parseInt(Number_of_Units), Integer.parseInt(Price));


                AboutModelClass aboutModelClass = new AboutModelClass(ProductCategory, Details, ProductName,Measure, Integer.parseInt(Discount_Price),
                        Integer.parseInt(Number_of_Units), Integer.parseInt(Price), null);

                uploadImage(Images, productModelClass, aboutModelClass);



            }
        });



    }



    private void uploadImage(ArrayList<String> images, ProductModelClass productModelClass, AboutModelClass aboutModelClass)
    {
        if (images.size() != 0)
        {
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Uploading 0/" + images.size());
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(false);
            progressDialog.show();

            StorageReference reference = firebaseStorage.getReference();
            List<String> imageUrlList = new ArrayList<>();

            for (int i = 0; i < images.size(); i++)
            {
                StorageReference storageReference = reference.child("Products").child(UUID.randomUUID().toString());
                Uri image = Uri.parse(images.toString());
                Log.i("check", images.get(i));
                //storageReference.putFile(Uri.parse(Images.get(i))).
                //storageReference.putFile(Uri.fromFile(new File(String.valueOf(Images)))).


                storageReference.putFile(Uri.parse("file://" + images.get(i))).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
                {
                    @Override
                    public void onSuccess(@NonNull UploadTask.TaskSnapshot taskSnapshot)
                    {
                        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
                        {
                            @Override
                            public void onSuccess(@NonNull Uri uri)
                            {
                                counter++;
                                progressDialog.setMessage("Uploading "+ counter + "/" + images.size());
                               // Toast.makeText(getApplicationContext(), "Uploading....", Toast.LENGTH_SHORT).show();
                                imageUrlList.add(uri.toString());

                                productModelClass.setImage_Primary(imageUrlList.get(0));

                                aboutModelClass.setImages(imageUrlList);

                                if(counter == images.size())
                                {
                                    db.collection("Store").document("uniquename.TFTVHvZaHOIxjYLnHvwc").collection("products").add(productModelClass).addOnSuccessListener(new OnSuccessListener<DocumentReference>()
                                    {
                                        @Override
                                        public void onSuccess(@NonNull DocumentReference documentReference)
                                        {
                                            documentReference.collection("about").document("product_id").set(aboutModelClass).addOnSuccessListener(new OnSuccessListener<Void>()
                                            {
                                                @Override
                                                public void onSuccess(@NonNull Void unused)
                                                {
                                                    progressDialog.dismiss();
                                                    Toast.makeText(getApplicationContext(), "Product Added Successfully!", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                        }
                                    }).addOnFailureListener(new OnFailureListener()
                                    {
                                        @Override
                                        public void onFailure(@NonNull Exception e)
                                        {
                                            progressDialog.dismiss();
                                            Toast.makeText(getApplicationContext(), "Error Adding Product", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }

                               // Log.i("showcount", imageUrlList.toString());
                            }
                        }).addOnFailureListener(new OnFailureListener()
                        {
                            @Override
                            public void onFailure(@NonNull Exception e)
                            {
                                storageReference.delete();
                                Toast.makeText(getApplicationContext(), "Couldn't save images", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener()
                {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        progressDialog.setMessage("Uploading " + counter + "/" + images.size());
                        counter++;
                        Toast.makeText(getApplicationContext(), "Couldn't upload images", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }


}