package com.marquedo.marquedo;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.anstrontechnologies.corehelper.AnstronCoreHelper;
import com.darsh.multipleimageselect.activities.AlbumSelectActivity;
import com.darsh.multipleimageselect.helpers.Constants;
import com.darsh.multipleimageselect.models.Image;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.iceteck.silicompressorr.SiliCompressor;
import com.marquedo.marquedo.secondary.PnS.ServiceModelClass;
import com.marquedo.marquedo.ui.Prod_n_Cat.Product.AboutModelClass;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class profileSetupAddServicePageActivity extends AppCompatActivity
{
    private String ServiceName, ServiceCategory;

    private int count = 3;

    private RecyclerView recyclerView;
    private imageAdapter imageAdapter;

    private Button AddImages, AddService;
    private EditText ServicePrice, ServiceDiscount, ServiceDetails, ServiceMeasure, NumberofHours;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();

    private ArrayList<String> Images = new ArrayList<>();
    private ActivityResultLauncher<Intent> getResult;
    private List<String> imageUrlList = new ArrayList<>();

    AnstronCoreHelper coreHelper;

    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup_service_page);


        recyclerView = findViewById(R.id.service_images_recyclerView);
        AddImages = findViewById(R.id.add_service_images);
        AddService = findViewById(R.id.add_service_button);
        ServicePrice = findViewById(R.id.service_price);
        ServiceDiscount = findViewById(R.id.service_discount);
        ServiceDetails = findViewById(R.id.service_details);
        ServiceMeasure = findViewById(R.id.service_measure);
        NumberofHours = findViewById(R.id.number_of_hours);


        imageAdapter = new imageAdapter(Images);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(imageAdapter);


        Bundle intent = getIntent().getExtras();
        ServiceName = intent.get("name").toString();
        ServiceCategory = intent.get("category").toString();



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


        AddService.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               /* if (Images.size() != 0)
                {
                    StorageReference reference = firebaseStorage.getReference();
                    for (int i = 0; i < Images.size(); i++)
                    {
                        StorageReference storageReference = reference.child(UUID.randomUUID().toString());
                        Uri image = Uri.parse(Images.toString());
                        Log.i("check", Images.get(i));
                        //storageReference.putFile(Uri.parse(Images.get(i))).
                        //storageReference.putFile(Uri.fromFile(new File(String.valueOf(Images)))).

                        storageReference.putFile(Uri.parse("file://" + Images.get(i))).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
                        {
                            @Override
                            public void onSuccess(@NonNull UploadTask.TaskSnapshot taskSnapshot)
                            {
                                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
                                {
                                    @Override
                                    public void onSuccess(@NonNull Uri uri)
                                    {
                                        Toast.makeText(getApplicationContext(), "Uploading....", Toast.LENGTH_SHORT).show();
                                        imageUrlList.add(uri.toString());
                                        Log.i("showcount", imageUrlList.toString());
                                    }
                                }).addOnFailureListener(new OnFailureListener()
                                {
                                    @Override
                                    public void onFailure(@NonNull Exception e)
                                    {
                                        storageReference.delete();
                                        Toast.makeText(getApplicationContext(), "Error" + e, Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }


                }*/

                String Measure = ServiceMeasure.getText().toString();
                String Number_of_Hours = NumberofHours.getText().toString();
                String Price = ServicePrice.getText().toString();
                String Discount_Price = ServiceDiscount.getText().toString();
                String Details = ServiceDetails.getText().toString();


                ServiceModelClass serviceModelClass = new ServiceModelClass(null,ServiceName, ServiceCategory, Measure, Details, Integer.parseInt(Discount_Price)
                        , Integer.parseInt(Number_of_Hours), Integer.parseInt(Price),null);

                uploadImageList(Images, serviceModelClass);

                /*db.collection("Store").document("uniquename.TFTVHvZaHOIxjYLnHvwc").collection("services").add(serviceModelClass).addOnSuccessListener(new OnSuccessListener<DocumentReference>()
                {
                    @Override
                    public void onSuccess(@NonNull DocumentReference documentReference)
                    {
                        Toast.makeText(getApplicationContext(), "Service Added Successfully!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener()
                {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(getApplicationContext(), "Error Adding Service", Toast.LENGTH_SHORT).show();
                    }
                });*/
            }
        });
    }


    private void uploadImageList(List<String> newImages, ServiceModelClass serviceModelClass)
    {
        if (newImages.size() != 0)
        {
            ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Uploading 0/" + newImages.size());
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setCancelable(false);
            progressDialog.show();


            StorageReference reference = firebaseStorage.getReference();
            coreHelper = new AnstronCoreHelper(this);
            List<String> imageUrlList = new ArrayList<>();

            for (int i = 0; i < newImages.size(); i++)
            {
                StorageReference storageReference = reference.child("Services").child(UUID.randomUUID().toString());
                Uri image = Uri.parse(newImages.toString());
                Log.i("check", newImages.get(i));
                //storageReference.putFile(Uri.parse(Images.get(i))).
                //storageReference.putFile(Uri.fromFile(new File(String.valueOf(Images)))).

                storageReference.putFile(Uri.parse("file://" + newImages.get(i))).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
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
                                progressDialog.setMessage("Uploading "+ counter + "/" + newImages.size());
                               // Toast.makeText(getApplicationContext(), "Uploading....", Toast.LENGTH_SHORT).show();
                                imageUrlList.add(uri.toString());

                                serviceModelClass.setImages(imageUrlList);
                                serviceModelClass.setImage_Primary(imageUrlList.get(0));

                                if(counter == newImages.size())
                                {
                                    db.collection("Store").document("uniquename.TFTVHvZaHOIxjYLnHvwc").collection("services").add(serviceModelClass).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(@NonNull DocumentReference documentReference)
                                        {
                                            progressDialog.dismiss();

                                            count--;

                                            SharedPreferences sharedPreferences = getSharedPreferences("servicecount", MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putInt("countvalue", count);
                                            editor.apply();

                                            loadData();


                                            //Toast.makeText(getApplicationContext(), "Service Added Successfully!", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e)
                                        {
                                            progressDialog.dismiss();
                                            Toast.makeText(getApplicationContext(), "Error Adding Service", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }

                                Log.i("showcount", imageUrlList.toString());
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

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        progressDialog.setMessage("Uploading " + counter + "/" + newImages.size());
                        counter++;
                        Toast.makeText(getApplicationContext(), "Couldn't upload", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }

    private void loadData()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("servicecount", MODE_PRIVATE);
        int check = sharedPreferences.getInt("countvalue", count);
        if(check == 2)
        {

        }
        else if(check == 1)
        {

        }
        else if(check == 0)
        {

        }


    }
}