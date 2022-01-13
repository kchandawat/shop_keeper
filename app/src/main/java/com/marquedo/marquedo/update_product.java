package com.marquedo.marquedo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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
import com.darsh.multipleimageselect.models.Image;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.marquedo.marquedo.ui.Prod_n_Cat.Product.AboutModelClass;
import com.marquedo.marquedo.ui.Prod_n_Cat.Product.ProductModelClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


public class update_product extends AppCompatActivity
{

    private String position;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();;
    private EditText category, name, details, price, discount_price, measure, number_of_prod;
    private Button preview, update;
    private TextInputLayout productCategoryTIL;
    private BottomSheetDialog bottomSheetDialog;
    private BottomSheetDialog addCatBottomSheet;


    private RecyclerView recyclerView;
    private AboutModelClass aboutModelClass;
    private MaterialButton add_images_button;
    private imageAdapter2 imageAdapter2;
    //private imageAdapter imageAdapter;
    private ActivityResultLauncher<Intent> getResult;


    List<String> Images = new ArrayList<>();
    List<String> newImages = new ArrayList<>();
    List<String> prevImages = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);


        preview = findViewById(R.id.show_preview);
        update = findViewById(R.id.update_product_button);
//        category = findViewById(R.id.prodCategory);
        productCategoryTIL = findViewById(R.id.product_category_til);
        name = findViewById(R.id.prodName);
        price = findViewById(R.id.prodPrice);
        number_of_prod = findViewById(R.id.num_of_prod);
        measure = findViewById(R.id.prodMeasure);
        discount_price = findViewById(R.id.prodDiscount);
        details = findViewById(R.id.prodDetails);
        add_images_button = findViewById(R.id.addImage_updateProd);
        recyclerView = findViewById(R.id.update_prod_recyclerView);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(null);

        bottomSheetDialog = new BottomSheetDialog(this, R.style.CustomAlertDialog);
        bottomSheetDialog.setContentView(R.layout.fragment_new_product_category);
        MaterialButton close = bottomSheetDialog.findViewById(R.id.close_sheet);
        MaterialButton addCategory = bottomSheetDialog.findViewById(R.id.add_new_product_category_button);
//        RecyclerView recyclerView = bottomSheetDialog.findViewById(R.id.recyclerview);
        addCatBottomSheet = new BottomSheetDialog(this, R.style.CustomAlertDialog);
        addCatBottomSheet.setContentView(R.layout.fragment_add_category);
        MaterialButton close2 = addCatBottomSheet.findViewById(R.id.close_sheet);

        Objects.requireNonNull(productCategoryTIL.getEditText()).setOnClickListener(view -> bottomSheetDialog.show());


        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCatBottomSheet.show();
            }
        });

        close2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCatBottomSheet.dismiss();
            }
        });



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
                    Toast.makeText(update_product.this, "6 Images are already selected. Delete some to choose more", Toast.LENGTH_LONG).show();
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
               ArrayList<Image> images = result.getData().getParcelableArrayListExtra(Constants.INTENT_EXTRA_IMAGES);
                //Images.clear();
                int count = Images.size();
                for (int i = 0; i < images.size(); i++)
                {
                    Images.add(images.get(i).path);
                    newImages.add(images.get(i).path);
                }
                imageAdapter2.notifyItemRangeInserted(count, images.size());
                //imageAdapter = new imageAdapter(Images);
                //recyclerView.setAdapter(imageAdapter);
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
                List<String> Urls = (List<String>) documentSnapshot.get("Images");

                prevImages = Urls;

                imageAdapter2 = new imageAdapter2(Urls);
                recyclerView.setAdapter(imageAdapter2);


                /*FirestoreRecyclerOptions<AboutModelClass> options = new FirestoreRecyclerOptions.Builder<AboutModelClass>()
                        .setQuery(query, AboutModelClass.class)
                        .build();

                imageAdapter1 = new imageAdapter1(options);
                recyclerView.setAdapter(imageAdapter1);*/

                aboutModelClass = documentSnapshot.toObject(AboutModelClass.class);
                //List<String> Images = aboutModelClass.getUrls();
                //String Category = documentSnapshot.getString("Category");
                //String Price = String.valueOf(documentSnapshot.get("Price"));
                String Category = aboutModelClass.getCategory();
                String Name = aboutModelClass.getName();
                String Details = aboutModelClass.getDetails();
                String Number_of_product = String.valueOf(aboutModelClass.getNumber_of_Units());
                String Measure = aboutModelClass.getUnit_Measure();
                String Price = String.valueOf(aboutModelClass.getPrice());
                String Discount_price = String.valueOf(aboutModelClass.getDiscount_Price());


                //aboutModelClass = documentSnapshot.get("Category", AboutModelClass.class);
                //category.setText(aboutModelClass.getCategory());
//                category.setText(Category);

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


        //Show preview
        preview.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(update_product.this, preview_product.class);
                intent.putExtra("key", position);
                startActivity(intent);


            }
        });


        update.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String Category = category.getText().toString();
                String Name = name.getText().toString();
                String Measure = measure.getText().toString();
                String Number_of_Products = number_of_prod.getText().toString();
                String Price = price.getText().toString();
                String Discount_Price = discount_price.getText().toString();
                String Details = details.getText().toString();
                String image = "https://firebasestorage.googleapis.com/v0/b/marquedo-a6afd.appspot.com/o/picture7.jpeg?alt=media&token=b1368711-5284-4a32-bd8a-83c86edda3ed";

                AboutModelClass aboutModelClass = new AboutModelClass(Category,Details, Name, Measure, Integer.parseInt(Discount_Price),
                        Integer.parseInt(Number_of_Products), Integer.parseInt(Price), null);

                ProductModelClass productModelClass = new ProductModelClass(image,Name, Measure, Integer.parseInt(Discount_Price),
                        Integer.parseInt(Number_of_Products), Integer.parseInt(Price));


               /*db.collection("Store").document("uniquename.TFTVHvZaHOIxjYLnHvwc").collection("products").document(position).collection("about").document("product_id").set(aboutModelClass).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(@NonNull Void unused) 
                    {
                        db.collection("Store").document("uniquename.TFTVHvZaHOIxjYLnHvwc").collection("products").document(position).set(productModelClass);
                        Toast.makeText(update_product.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                    }
                });*/

                uploadImageList(newImages, aboutModelClass);

            }
        });

    }

    private void uploadImageList(List<String> newImages, AboutModelClass aboutModelClass)
    {
        StorageReference reference = firebaseStorage.getReference();
        List<String> imageUrlList = new ArrayList<>();

        for(String imagePath : newImages)
        {
            StorageReference imageReference = reference.child(UUID.randomUUID().toString());

            imageReference.putFile(Uri.parse(imagePath)).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
            {
                @Override
                public void onSuccess(@NonNull UploadTask.TaskSnapshot taskSnapshot)
                {
                    imageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
                    {
                        @Override
                        public void onSuccess(@NonNull Uri uri)
                        {
                            imageUrlList.add(uri.toString());

                            if(imageUrlList.size() == newImages.size())
                            {
                                List<String> images = new ArrayList<>(prevImages);
                                images.addAll(imageUrlList);

                                //aboutModelClass.setUrls(images);

                                db.collection("Store").document("uniquename.TFTVHvZaHOIxjYLnHvwc").collection("products").document(position).collection("about").document("product_id").set(aboutModelClass).addOnSuccessListener(new OnSuccessListener<Void>()
                                {
                                    @Override
                                    public void onSuccess(@NonNull Void unused)
                                    {
                                        Toast.makeText(update_product.this, "Updated Successfully!", Toast.LENGTH_SHORT).show();
                                    }
                                });

                            }
                        }
                    });
                }
            });
        }

    }
}
