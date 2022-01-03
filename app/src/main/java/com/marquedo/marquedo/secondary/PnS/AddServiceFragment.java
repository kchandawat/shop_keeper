package com.marquedo.marquedo.secondary.PnS;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.darsh.multipleimageselect.activities.AlbumSelectActivity;
import com.darsh.multipleimageselect.helpers.Constants;
import com.darsh.multipleimageselect.models.Image;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.marquedo.marquedo.R;
import com.marquedo.marquedo.imageAdapter;
import com.marquedo.marquedo.progress;
import com.marquedo.marquedo.ui.Prod_n_Cat.Product.AboutModelClass;
import com.marquedo.marquedo.update_product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class AddServiceFragment extends Fragment
{
    public AddServiceFragment()
    {
        // Required empty public constructor
    }

   /* public static AddServiceFragment newInstance(String param1, String param2) {
        AddServiceFragment fragment = new AddServiceFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }*/

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();

    private EditText name, category, price, discount_price, measure, number_of_hours,details;
    private Button addService, addServiceImages;

    private RecyclerView recyclerView;
    private imageAdapter imageAdapter;


    private ArrayList<String> Images = new ArrayList<>();
    private ActivityResultLauncher<Intent> getResult;
    private List<String> imageUrlList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_service, container, false);

        addService = v.findViewById(R.id.add_service_button);
        addServiceImages = v.findViewById(R.id.add_service_images);
        recyclerView = v.findViewById(R.id.add_service_recyclerView);
        name = v.findViewById(R.id.serviceName);
        category = v.findViewById(R.id.serviceCategory);
        price = v.findViewById(R.id.servicePrice);
        discount_price = v.findViewById(R.id.serviceDiscount);
        measure = v.findViewById(R.id.serviceMeasure);
        number_of_hours = v.findViewById(R.id.num_of_hours);
        details = v.findViewById(R.id.serviceDetails);

        imageAdapter = new imageAdapter(Images);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(imageAdapter);


        addServiceImages.setOnClickListener(new View.OnClickListener()
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



        addService.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (Images.size() != 0)
                {
                    StorageReference reference = firebaseStorage.getReference();
                    for (int i = 0; i < Images.size(); i++)
                    {
                        StorageReference storageReference = reference.child(UUID.randomUUID().toString());
                        Uri image = Uri.parse(Images.toString());
                        Log.i("check", image.toString());
                        storageReference.putFile(Uri.parse(Images.get(i))).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
                        {
                            @Override
                            public void onSuccess(@NonNull UploadTask.TaskSnapshot taskSnapshot)
                            {
                                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
                                {
                                    @Override
                                    public void onSuccess(@NonNull Uri uri)
                                    {
                                        imageUrlList.add(uri.toString());
                                        Log.i("showcount", imageUrlList.toString());
                                    }
                                }).addOnFailureListener(new OnFailureListener()
                                {
                                    @Override
                                    public void onFailure(@NonNull Exception e)
                                    {
                                        storageReference.delete();
                                        Toast.makeText(getContext(), "Error" + e, Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }


                }

                String Category = category.getText().toString();
                String Name = name.getText().toString();
                String Measure = measure.getText().toString();
                String Number_of_Hours = number_of_hours.getText().toString();
                String Price = price.getText().toString();
                String Discount_Price = discount_price.getText().toString();
                String Details = details.getText().toString();


                ServiceModelClass serviceModelClass = new ServiceModelClass(Name, Category, Measure, Details, Integer.parseInt(Discount_Price)
                        , Integer.parseInt(Number_of_Hours), Integer.parseInt(Price), imageUrlList);

                //addImages(Images, serviceModelClass);

                //addImages(Images);

                db.collection("Store").document("uniquename.TFTVHvZaHOIxjYLnHvwc").collection("services").add(serviceModelClass).addOnSuccessListener(new OnSuccessListener<DocumentReference>()
                {
                    @Override
                    public void onSuccess(@NonNull DocumentReference documentReference)
                    {
                        Toast.makeText(getContext(), "Service Added Successfully!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener()
                {
                    @Override
                    public void onFailure(@NonNull Exception e)
                    {
                        Toast.makeText(getContext(), "Error Adding Service", Toast.LENGTH_SHORT).show();
                    }
                });


            }

            /*private void addImages(ArrayList<String> images, ServiceModelClass serviceModelClass)
            {
                List<String> imageUrlList = new ArrayList<>();
                if (Images.size() != 0) {
                    StorageReference reference = firebaseStorage.getReference();
                    for (int i = 0; i < images.size(); i++)
                    {
                        StorageReference storageReference = reference.child(UUID.randomUUID().toString());
                        storageReference.putFile(Uri.parse(images.get(i))).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
                        {
                            @Override
                            public void onSuccess(@NonNull UploadTask.TaskSnapshot taskSnapshot)
                            {
                                storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
                                {
                                    @Override
                                    public void onSuccess(@NonNull Uri uri) {
                                        imageUrlList.add(uri.toString());
                                        Log.i("showcount", images.toString());

                                        if (imageUrlList.size() == images.size()) {
                                            serviceModelClass.setImages(imageUrlList);

                                            db.collection("Store").document("uniquename.TFTVHvZaHOIxjYLnHvwc").collection("services").add(serviceModelClass).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                @Override
                                                public void onSuccess(@NonNull DocumentReference documentReference) {
                                                    Toast.makeText(getContext(), "Product Added Successfully!", Toast.LENGTH_SHORT).show();
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(getContext(), "Error Adding Product", Toast.LENGTH_SHORT).show();
                                                }
                                            });

                                        }
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        storageReference.delete();
                                        Toast.makeText(getContext(), "Error" + e, Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        });
                    }

                }

    }*/


        });

        return v;
    }

    private void uploadImages(ArrayList<String> images)
    {
        if (Images.size() != 0)
        {
            StorageReference reference = firebaseStorage.getReference();
            for (int i = 0; i < images.size(); i++)
            {
                StorageReference storageReference = reference.child(UUID.randomUUID().toString());
                storageReference.putFile(Uri.parse(images.get(i))).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
                {
                    @Override
                    public void onSuccess(@NonNull UploadTask.TaskSnapshot taskSnapshot)
                    {
                        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
                        {
                            @Override
                            public void onSuccess(@NonNull Uri uri)
                            {
                                imageUrlList.add(uri.toString());
                                Log.i("showcount", imageUrlList.toString());

                               /* if(imageUrlList.size() == images.size())
                                {
                                    ServiceModelClass serviceModelClass = new ServiceModelClass(null,null,
                                            null,null,null,null,null,imageUrlList);
                                    serviceModelClass.setImages(imageUrlList);

                                    db.collection("Store").document("uniquename.TFTVHvZaHOIxjYLnHvwc").collection("services").add(serviceModelClass).addOnSuccessListener(new OnSuccessListener<DocumentReference>()
                                    {
                                        @Override
                                        public void onSuccess(@NonNull DocumentReference documentReference)
                                        {
                                            Toast.makeText(getContext(), "Product Added Successfully!", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener()
                                    {
                                        @Override
                                        public void onFailure(@NonNull Exception e)
                                        {
                                            Toast.makeText(getContext(), "Error Adding Product", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                }*/
                            }
                        }).addOnFailureListener(new OnFailureListener()
                        {
                            @Override
                            public void onFailure(@NonNull Exception e)
                            {
                                storageReference.delete();
                                Toast.makeText(getContext(), "Error" + e, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }


        }
    }


    /*private void addImages(ArrayList<String> images, ServiceModelClass serviceModelClass)
    {
        List<String> imageUrlList = new ArrayList<>();
        if(Images.size() != 0)
        {
            StorageReference reference = firebaseStorage.getReference();
            for(int i = 0; i<images.size(); i++ )
            {
                StorageReference storageReference = reference.child(UUID.randomUUID().toString());
                storageReference.putFile(Uri.parse(images.get(i))).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
                {
                    @Override
                    public void onSuccess(@NonNull UploadTask.TaskSnapshot taskSnapshot)
                    {
                        storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
                        {
                            @Override
                            public void onSuccess(@NonNull Uri uri)
                            {
                                imageUrlList.add(uri.toString());
                                Log.i("showcount", images.toString());

                                if(imageUrlList.size() == images.size())
                                {
                                    serviceModelClass.setImages(imageUrlList);

                                    db.collection("Store").document("uniquename.TFTVHvZaHOIxjYLnHvwc").collection("services").add(serviceModelClass).addOnSuccessListener(new OnSuccessListener<DocumentReference>()
                                    {
                                        @Override
                                        public void onSuccess(@NonNull DocumentReference documentReference)
                                        {
                                            Toast.makeText(getContext(), "Product Added Successfully!", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener()
                                    {
                                        @Override
                                        public void onFailure(@NonNull Exception e)
                                        {
                                            Toast.makeText(getContext(), "Error Adding Product", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                }
                            }
                        }).addOnFailureListener(new OnFailureListener()
                        {
                            @Override
                            public void onFailure(@NonNull Exception e)
                            {
                                storageReference.delete();
                                Toast.makeText(getContext(), "Error" + e, Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }

        }

    }*/
}