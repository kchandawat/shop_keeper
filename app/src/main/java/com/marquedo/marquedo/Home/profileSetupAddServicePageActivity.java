package com.marquedo.marquedo.Home;

import static android.content.ContentValues.TAG;

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
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import com.anstrontechnologies.corehelper.AnstronCoreHelper;
import com.darsh.multipleimageselect.activities.AlbumSelectActivity;
import com.darsh.multipleimageselect.helpers.Constants;
import com.darsh.multipleimageselect.models.Image;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Transaction;
import com.google.firebase.firestore.WriteBatch;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
//import com.iceteck.silicompressorr.SiliCompressor;
import com.marquedo.marquedo.CategoriesRecyclerViewAdapter;
import com.marquedo.marquedo.CheckboxData;
import com.marquedo.marquedo.ProductsNCategories.imageAdapter2;
import com.marquedo.marquedo.R;
import com.marquedo.marquedo.ProductsNCategories.imageAdapter;
import com.marquedo.marquedo.Snack;
import com.marquedo.marquedo.secondary.PnS.ServiceModelClass;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class profileSetupAddServicePageActivity extends AppCompatActivity
{
    private String servicename, mode;

    private int count = 0;

    private RecyclerView recyclerView;
    private CategoriesRecyclerViewAdapter adapter;
    private com.marquedo.marquedo.ProductsNCategories.imageAdapter imageAdapter;
    private TextInputLayout categoriesTIL;
    private String CategoriesString;


    private Button AddImages, AddService;
    private EditText ServiceName, ServiceCategory, ServicePrice, ServiceDiscount, ServiceDetails, ServiceMeasure, NumberofHours;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();

    private DatabaseReference databaseReference;
    private FirebaseDatabase firebaseDatabase =FirebaseDatabase.getInstance();


    private ArrayList<String> Images = new ArrayList<>();
    private ActivityResultLauncher<Intent> getResult;
    private List<String> imageUrlList = new ArrayList<>();
    private Set<String> categories = new HashSet<String>();
    private Set<String> check;
    private final List<String> currentSelectedItems = new ArrayList<>();


    private SharedPreferences.Editor myEdit;
    private SharedPreferences sharedPreferences2;


    private BottomSheetDialog serviceAddedSuccessBM ;
    private BottomSheetDialog addCategoryDialog;

    private ServiceNameModelClass serviceNameModelClass;

    private imageAdapter2 imageAdapter2;

    private Snack snack;

    DocumentReference serviceRef = db.collection("Store").
            document("uniquename.TFTVHvZaHOIxjYLnHvwc");


//    AnstronCoreHelper coreHelper;

    int counter;

//    SharedPreferences sharedPreferences = getSharedPreferences("noOfServices", MODE_PRIVATE);
//    String tempCounter = sharedPreferences.getString("counter","");


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_profile_setup_service_page);

        sharedPreferences2 = getSharedPreferences("Categories",MODE_PRIVATE);
        SharedPreferences sharedPreferences = getSharedPreferences("servicecount", MODE_PRIVATE);
        count = sharedPreferences.getInt("countvalue", 3);

        recyclerView = findViewById(R.id.service_images_recyclerView);
        AddImages = findViewById(R.id.add_service_images);
        AddService = findViewById(R.id.add_service_button);
        ServiceCategory = findViewById(R.id.service_category);
        ServiceName = findViewById(R.id.service_name);
        ServicePrice = findViewById(R.id.service_price);
        ServiceDiscount = findViewById(R.id.service_discount);
        ServiceDetails = findViewById(R.id.service_details);
        ServiceMeasure = findViewById(R.id.service_measure);
        NumberofHours = findViewById(R.id.number_of_hours);
        categoriesTIL = findViewById(R.id.TILCategory);
        myEdit = sharedPreferences2.edit();

        snack = new Snack(getApplicationContext());


        imageAdapter = new imageAdapter(Images);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(imageAdapter);


        serviceAddedSuccessBM = new BottomSheetDialog(this, R.style.CustomAlertDialog);
        serviceAddedSuccessBM.setContentView(R.layout.home_fragment_added_success);

        Intent nameIntent = getIntent();
        servicename = nameIntent.getStringExtra("name");
        ServiceName.setText(servicename);
        mode = nameIntent.getStringExtra("mode");


        Intent intent = getIntent();
        String Key = intent.getStringExtra("key");



        Set<String> newt = sharedPreferences2.getStringSet("ServCatSet", new HashSet<>());
        System.out.println("@@@@@@@@@@@ newt"+newt+"@@@@@@@@@@@@@");
        Log.i("newtCheck", newt.toString());

        if(newt.size() == 0)
        {
            getCategories();
        }

        else{
            System.out.println("$$$$$ newt" + newt + "%%%%%%%%%");
            categories = newt;
        }



        if(mode.equals("0"))
        {
            //Auto complete data fills the complete form
            databaseReference = firebaseDatabase.getReference("Services").child(Key);

            databaseReference.addListenerForSingleValueEvent(new ValueEventListener()
            {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot)
                {
                    if(snapshot.exists())
                    {
                        // List<String> Urls = (List<String>) snapshot.;
                        //String Number_of_product = String.valueOf(aboutModelClass.getNumber_of_Units());
                        serviceNameModelClass = snapshot.getValue(ServiceNameModelClass.class);

                        Log.i("checknamelist", serviceNameModelClass.getName());
                        ServiceName.setText(serviceNameModelClass.getName());
                        ServiceCategory.setText(serviceNameModelClass.getCategory());
                        ServicePrice.setText(String.valueOf(serviceNameModelClass.getPrice()));
                        ServiceMeasure.setText(serviceNameModelClass.getMeasure());
                        ServiceDetails.setText(serviceNameModelClass.getDetails());
                        ServiceDiscount.setText(String.valueOf(serviceNameModelClass.getDiscount_price()));
                        NumberofHours.setText(String.valueOf(serviceNameModelClass.getNumber_of_hours()));

                        List<String> Urls = serviceNameModelClass.getImages();

                        Log.i("listofimage", Urls.toString());

                        imageAdapter2 = new imageAdapter2(Urls);
                        recyclerView.setAdapter(imageAdapter2);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error)
                {

                }
            });
        }


       /* databaseReference.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if(snapshot.exists())
                {
                   // List<String> Urls = (List<String>) snapshot.;
                    //String Number_of_product = String.valueOf(aboutModelClass.getNumber_of_Units());
                    serviceNameModelClass = snapshot.getValue(ServiceNameModelClass.class);

                    Log.i("checknamelist", serviceNameModelClass.getName());
                    ServiceName.setText(serviceNameModelClass.getName());
                    ServiceCategory.setText(serviceNameModelClass.getCategory());
                    ServicePrice.setText(String.valueOf(serviceNameModelClass.getPrice()));
                    ServiceMeasure.setText(serviceNameModelClass.getMeasure());
                    ServiceDetails.setText(serviceNameModelClass.getDetails());
                    ServiceDiscount.setText(String.valueOf(serviceNameModelClass.getDiscount_price()));
                    NumberofHours.setText(String.valueOf(serviceNameModelClass.getNumber_of_hours()));

                    List<String> Urls = serviceNameModelClass.getImages();

                    Log.i("listofimage", Urls.toString());

                    imageAdapter2 = new imageAdapter2(Urls);
                    recyclerView.setAdapter(imageAdapter2);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        }); */

        //Query query = databaseReference.orderByKey().equalTo(Key);
       /* query.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if(snapshot.exists())
                {
                    for(DataSnapshot dataSnapshot : snapshot.getChildren())
                    {
                        ServiceName.setText(dataSnapshot.child("name").getValue(String.class));
                        ServiceCategory.setText(dataSnapshot.child("category").getValue(String.class));
                        ServicePrice.setText(dataSnapshot.child("price").getValue(String.class));
                        ServiceMeasure.setText(dataSnapshot.child("measure").getValue(String.class));
                        ServiceDetails.setText(dataSnapshot.child("details").getValue(String.class));
                        ServiceDiscount.setText(dataSnapshot.child("discount_price").getValue(String.class));
                        NumberofHours.setText(dataSnapshot.child("number_of_hours").getValue(String.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        }); */

//        loadData(1);
//        serviceAddedSuccessBM.dismiss();


        ServiceCategory.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                addCategoryDialog = new BottomSheetDialog(v.getContext(), R.style.CustomAlertDialog);
                addCategoryDialog.setContentView(R.layout.home_fragment_new_services_category);
                addCategoryDialog.show();
                RecyclerView categoriesRecyclerView = addCategoryDialog.findViewById(R.id.rvServiceCategories);
                MaterialButton addNewCategory = addCategoryDialog.findViewById(R.id.add_new_product_category_button);
                MaterialButton submitBtn = addCategoryDialog.findViewById(R.id.submit_button);
                CheckboxData checkboxData = new CheckboxData() {
                    @Override
                    public void onCheckboxCheck(String checkboxData) {
                        currentSelectedItems.add(checkboxData);
                        Log.d(TAG, currentSelectedItems.toString());
                    }

                    @Override
                    public void onCheckboxUnCheck(String checkboxData) {
                        currentSelectedItems.remove(checkboxData);
                        Log.d(TAG, currentSelectedItems.toString());
                    }
                };
                adapter = new CategoriesRecyclerViewAdapter(new ArrayList<>(categories), v.getContext(), checkboxData);
                categoriesRecyclerView.setAdapter(adapter);
                categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));

                submitBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (!currentSelectedItems.isEmpty()) {
                            for (int i = 0; i < currentSelectedItems.size(); i++) {
                                if (i == 0)
                                    Objects.requireNonNull(categoriesTIL.getEditText()).setText(currentSelectedItems.get(i));
                                else{
                                    CategoriesString = categoriesTIL.getEditText().getText().toString();
                                    String catString = CategoriesString + ", " + currentSelectedItems.get(i);
                                    categoriesTIL.getEditText().setText(catString);
                                }
                            }
                            addCategoryDialog.dismiss();
                        } else {
                            Toast.makeText(view.getContext(), "Please select a category...", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                addNewCategory.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view1) {
                        BottomSheetDialog addNewCatSheet = new BottomSheetDialog(view1.getContext(),
                                R.style.CustomAlertDialog);
                        addNewCatSheet.setContentView(R.layout.productsncategories_fragment_add_category);
                        addNewCatSheet.show();
                        EditText newcat = addNewCatSheet.findViewById(R.id.etnewCategory);
                        Button addbtn = addNewCatSheet.findViewById(R.id.add_button);

                        addbtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view2) {
                                String newCat = newcat.getText().toString();
                                System.out.println(newCat);
                                if(newCat.equals("")){
                                    System.out.println("abifejjknlad");
                                    snack = new Snack(view2.getContext());
                                    snack.snackBar(newcat, "Please enter a Category name");
                                }
                                else{
                                    db.collection("Store")
                                            .document("uniquename.TFTVHvZaHOIxjYLnHvwc")
                                            .update("categories.service",
                                                    FieldValue.arrayUnion(newCat)).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            getCategories();
                                            ProgressDialog dialog = ProgressDialog.show(view2.getContext(), "",
                                                "Adding New Category. Please wait...", true);
                                            Handler handler = new Handler();
                                            handler.postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    {
                                                        dialog.dismiss();
                                                        System.out.println("YYYYYY" + sharedPreferences2.getStringSet("ServCatSet", new HashSet<>()) + "YYYYYYy");
                                                        adapter.updateData(sharedPreferences2.getStringSet("ServCatSet", new HashSet<>()));
                                                        addNewCatSheet.dismiss();
                                                        Toast.makeText(view1.getContext(), "New Category Added Successfully", Toast.LENGTH_LONG).show();
                                                    }
                                                }
                                            }, 2000);// 2000 milliseconds = 2seconds

                                            }
                                    });

                                }

                            }
                        });

                    }
                });
            }
        });









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
                String Name = ServiceName.getText().toString();
                String Category = ServiceCategory.getText().toString();
                String Measure = ServiceMeasure.getText().toString();
                String Number_of_Hours = NumberofHours.getText().toString();
                String Price = ServicePrice.getText().toString();
                String Discount_Price = ServiceDiscount.getText().toString();
                String Details = ServiceDetails.getText().toString();


                if(!(Category.equals("")) && !(Measure.equals("")) && !(Number_of_Hours.equals("")) && !(Price.equals("")))
                {
                    ServiceModelClass serviceModelClass = new ServiceModelClass(null,Name, Category, Measure, Details, Integer.parseInt(Discount_Price)
                            , Integer.parseInt(Number_of_Hours), Integer.parseInt(Price),null);

                    uploadImageList(Images, serviceModelClass);
                }
                else
                {
                    snack.snackBar(ServicePrice, "Please enter all the details");
                }
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



    private void getCategories(){
        System.out.println("%%%%%% inside getcat %%%%%%55");
        serviceRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                categories.clear();
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        List<String> list = (List<String>) ((Map)document.getData().get("categories")).get("service");
                        for(String i : list){
                            categories.add(i);
                        }
                        Log.i("testingCat", categories.toString());

                        myEdit = sharedPreferences2.edit();
                        myEdit.putStringSet("ServCatSet", categories);
                        myEdit.apply();
                        check = sharedPreferences2.getStringSet("ServCatSet", new HashSet<>());
                        System.out.println(categories);

                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
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
//            coreHelper = new AnstronCoreHelper(this);
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
                                    DocumentReference storeRef = db.collection("Store")
                                            .document("uniquename.TFTVHvZaHOIxjYLnHvwc");
                                    storeRef.collection("services").add(serviceModelClass).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(@NonNull DocumentReference documentReference)
                                        {
                                            storeRef.update("categories.product", FieldValue.arrayUnion(serviceModelClass.getCategory()));
                                            databaseReference = firebaseDatabase.getReference("Services");
                                            databaseReference.push().setValue(serviceModelClass);

                                            progressDialog.dismiss();

                                            if(count != 0)
                                            {
                                                count--;
                                            }

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
        TextView remainingService = serviceAddedSuccessBM.findViewById(R.id.remainingService);
        Button addMore = serviceAddedSuccessBM.findViewById(R.id.addMore_button);
        String text;
        int check = sharedPreferences.getInt("countvalue", 4);
//        remainingService.setText("Let's add "+ count+ " more products or services to complete your profile");
        if(check == 2)
        {
            remainingService.setText("Let's add 2 more products or services to complete your profile");
            serviceAddedSuccessBM.show();
            addMore.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(getApplicationContext(), addProductAndServicesProfilesetupActivity.class);
                    startActivity(intent);
                }
            });
        }
        else if(check == 1)
        {
            remainingService.setText("Let's add 1 more products or services to complete your profile");
            serviceAddedSuccessBM.show();
            addMore.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(getApplicationContext(), addProductAndServicesProfilesetupActivity.class);
                    startActivity(intent);
                }
            });
        }
        else if(check == 0)
        {
            remainingService.setText("Hurrah! Add more products and services to your shop.");
            serviceAddedSuccessBM.show();
            addMore.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(getApplicationContext(), addProductAndServicesProfilesetupActivity.class);
                    startActivity(intent);
                }
            });
        }

    }


}