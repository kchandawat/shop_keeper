package com.marquedo.marquedo.Home;

import static android.content.ContentValues.TAG;
import static com.marquedo.marquedo.OurConstants.SHOW_TIME;


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
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.darsh.multipleimageselect.activities.AlbumSelectActivity;
import com.darsh.multipleimageselect.helpers.Constants;
import com.darsh.multipleimageselect.models.Image;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.checkbox.MaterialCheckBox;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.marquedo.marquedo.AddProductVariant.ColourCodes;
import com.marquedo.marquedo.AddProductVariant.DataExtraction;
import com.marquedo.marquedo.AddProductVariant.GetColourVariants;
import com.marquedo.marquedo.AddProductVariant.GetVariants;
import com.marquedo.marquedo.AddProductVariant.ProductVariantRecyclerViewAdapter;
import com.marquedo.marquedo.AddProductVariant.RemoveClickListner;
import com.marquedo.marquedo.AddProductVariant.RemoveColourClickListner;
import com.marquedo.marquedo.AddProductVariant.TheData;
import com.marquedo.marquedo.AddProductVariant.VariantData;
import com.marquedo.marquedo.OrdersNEnquiries.Orders.Order_details_overview;
import com.marquedo.marquedo.R;
import com.marquedo.marquedo.datab.Variant;
import com.marquedo.marquedo.databinding.Progress6ProductVariantRecyclerviewBinding;
import com.marquedo.marquedo.ProductsNCategories.Product.AboutModelClass;
import com.marquedo.marquedo.ProductsNCategories.Product.ProductModelClass;
import com.marquedo.marquedo.ProductsNCategories.imageAdapter;
import com.marquedo.marquedo.models.Orders_details_overview_model;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class profileSetupAddProductPageActivity extends AppCompatActivity implements RemoveClickListner, RemoveColourClickListner, GetVariants {

    //private String ProductName, ProductCategory;

    private RecyclerView recyclerView;
    private com.marquedo.marquedo.ProductsNCategories.imageAdapter imageAdapter;

    private Button AddImages, AddVarient, AddProduct;
    private EditText ProdName, ProdCategory, ProdPrice, ProdDiscount, ProdDetails, ProdMeasure, NumberofProd;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();

    private ArrayList<String> Images = new ArrayList<>();
    private ActivityResultLauncher<Intent> getResult;
    private List<String> imageUrlList = new ArrayList<>();
    private Set<String> categories = new HashSet<String>();

    private BottomSheetDialog addCategoryDialog;

    private DatabaseReference databaseReference;

    int counter;

    private ProductNameModelClass productNameModelClass;

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

    //collection reference to products under store.
    CollectionReference productRef = db.collection("Store").
            document("uniquename.TFTVHvZaHOIxjYLnHvwc")
            .collection("products");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_profile_setup_product_page);

        //MAAZ START
        binding = DataBindingUtil.setContentView(this, R.layout.home_activity_profile_setup_product_page);
        MaterialButton addProductVariant = findViewById(R.id.add_new_product_variant_button);
        Button add_product_button = findViewById(R.id.add_product_button);


        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        Set<String> newt = sharedPreferences.getStringSet("ProdCatSet", new HashSet<String>());
        if(newt.size() == 0){
            getCategories();
            myEdit.putStringSet("ProdCatSet", categories);
            myEdit.commit();
        }
        else{
            categories = newt;
        }
        System.out.println("@@@@@@@@@@@" + newt + " " + "@@@@@@@@@@");




        myList = new ArrayList<>();
        colourList = new ArrayList<>();
        /*getVariants = new GetVariants() {
            @Override
            public List<VariantData> getV() {
                return myList;
            }

            @Override
            public int getVLSize() {
                return 0;
            }
        };

        getColourVariants = new GetColourVariants() {
            @Override
            public List<ColourData> getC() {
                return colourList;
            }

            @Override
            public int getCLSize() {
                return 0;
            }
        };*/

        addProductVariant.setOnClickListener(view ->
        {
            View view1 = getLayoutInflater().inflate(R.layout.progress_6_variant_card, null);
            BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
            bottomSheetDialog.setContentView(view1);

            variantRecyclerView = bottomSheetDialog.findViewById(R.id.variant_recyclerview);
            MaterialButton saveAndContinue = bottomSheetDialog.findViewById(R.id.save_and_continue);

//            variantData = new DataExtraction();
            /*variantData.setSizeName("");
            variantData.setPrice("");
            variantData.setDiscountedPrice("");
            myList.add(variantData);*/
            myList.add(new VariantData());

//            ArrayList<TheData> theDataArrayList = new ArrayList<>();
//            ArrayList<ColourData> colourDataArrayList = new ArrayList<>();
            //Create adapter
            productVariantRecyclerViewAdapter = new ProductVariantRecyclerViewAdapter(myList, prodList, this, this);
            /*binding.recyclerView.apply; {
                LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            }*/

            Objects.requireNonNull(variantRecyclerView).setAdapter(productVariantRecyclerViewAdapter);
            Window window = bottomSheetDialog.getWindow();
            window.setLayout(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.WRAP_CONTENT);
            //ExpandableLayout expandableLayout = view1.findViewById(R.id.expandable_layout);


            /*Log.d("PVAdap", "Main" + variantData.getSizeName());
            Log.d("PVAdap", "Main" + variantData.getPrice());
            Log.d("PVAdap", "Main" + variantData.getDiscountedPrice());
            Log.d("PVAdap", "Main" + myList.toString());
            productVariantRecyclerViewAdapter.notifyData(myList);*/

            TextView dropdown1 = view1.findViewById(R.id.add_size_variant);

            EditText sizeVariantName = view1.findViewById(R.id.variant_name);
            EditText variantPrice = view1.findViewById(R.id.variant_price);
            EditText variantDiscountedPrice = view1.findViewById(R.id.variant_discounted_price);
            MaterialButton new_color = view1.findViewById(R.id.add_new_color_variant_button);
            //ExpandableLayout expandableLayout1 = view1.findViewById(R.id.expandable_layout_1);
            //TextView dropdown2 = view1.findViewById(R.id.add_color_variant);

            ExtendedFloatingActionButton addVariantButton = view1.findViewById(R.id.add_variant_button);
            //dropdown1.setOnClickListener(v -> expandableLayout.toggle());
            addVariantButton.shrink();

            addVariantButton.setOnClickListener(view2 -> {
                /*if (sizeVariantName.getText().length()==0){
                    return;
                }
                if (variantPrice.getText().length()==0){
                    return;
                }
                if (color==0){
                    return;
                }*/

                /* size name
                   price
                   discounted price
                   colour */

                if (addVariantButton.isExtended()) {
                    myList.add(new VariantData());
                    productVariantRecyclerViewAdapter.notifyData(myList);
                /*sizeVariantName.setText("");
                variantPrice.setText("");*/
                } else {
                    addVariantButton.extend();
                    new Handler(Looper.getMainLooper()).postDelayed(addVariantButton::shrink, SHOW_TIME);
                }
            });

            bottomSheetDialog.setOnDismissListener(dialogInterface -> {
                myList.clear();
//                theDataArrayList.clear();
//                colourDataArrayList.clear();
                variantRecyclerView.invalidate();
            });

            variantRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);

                    if (dy > 10 && addVariantButton.isExtended()) {
                        addVariantButton.shrink();
                    }

                    if (dy > 10 && addVariantButton.isShown()) {
                        addVariantButton.hide();
                    }

                    // if the recycler view is
                    // scrolled above show the EFAB
                    if (dy < -10 && !addVariantButton.isShown()) {
                        addVariantButton.show();
                    }

                    // of the recycler view is at the first
                    // item always show the EFAB
                    if (!recyclerView.canScrollVertically(-1)) {
                        addVariantButton.show();
                    }
                }
            });

            saveAndContinue.setOnClickListener(view2 -> {
//                productVariantRecyclerViewAdapter.getVar();
                /*List<VariantData> list = productVariantRecyclerViewAdapter.getMVV();*/
//                int variantSize = productVariantRecyclerViewAdapter.getVVLSize();
                /*List<ColourData> colourDataList = productVariantRecyclerViewAdapter.getCCC();*/
//                int colourSize = productVariantRecyclerViewAdapter.getCCLSize();
                productVariantRecyclerViewAdapter.getMVV();
//                productVariantRecyclerViewAdapter.getMCC();
                /*Log.d("dataa", String.valueOf(variantSize));
                Log.d("dataa", String.valueOf(colourSize));*/

                new Handler(Looper.getMainLooper()).postDelayed(() -> {
                    List<TheData> theDataList = new ArrayList<>();
                    TheData theData = new TheData();
                    for (int i = 0; i < variantSize; i++) {
                        View variantRecyclerViewView = variantRecyclerView.getChildAt(i);
                        EditText nameEditText = variantRecyclerViewView.findViewById(R.id.variant_name);
                        String name = nameEditText.getText().toString();

//                    colourVariantRecyclerView.setItemViewCacheSize(colourList.size());
                        theData.setSizeName(name);
                        theDataList.add(theData);
                        Log.d("dataa", theDataList.get(i).getSizeName());
                    /*for (int i = 0; i < variantSize; i++) {
                        String sizename = myList.get(i).getSizeName();
                        Log.d("dataa", String.valueOf(variantSize));
//                        Log.d("dataa", sizename);*/
                        for (int j = 0; j < 2; j++) {
                            Log.d("dataa", String.valueOf(colourSize));
                            Log.d("dataa", myList.get(i).getColourCodes().get(j).getColourCode());
                        }
                    }
                }, SHOW_TIME);
//                for (int i = 0; i < variantSize; i++) {
                    /*View variantRecyclerViewView = variantRecyclerView.getChildAt(i);
                    EditText nameEditText = variantRecyclerViewView.findViewById(R.id.variant_name);
                    String name = nameEditText.getText().toString();

//                    colourVariantRecyclerView.setItemViewCacheSize(colourList.size());
                    theData.setSizeName(name);
                    theDataArrayList1.add(theData);
                    Log.d("dataa", theDataArrayList1.get(i).getSizeName());*/
//                    Log.d("dataa", myList.get(i).getSizeName());

//                    for (int j = 0; j < colourSize; j++) {
                        /*RecyclerView colourVariantRecyclerView = variantRecyclerViewView.findViewById(R.id.colour_variant_recyclerview);
                        View colourVariantRecyclerViewView = colourVariantRecyclerView.getChildAt(j);
                        MaterialCardView materialCardView = colourVariantRecyclerView.findViewById(R.id.colour_cardview);
//                        theData.setColourCodes(colourList).get(j).getColourCode();
                        String colour = String.valueOf(materialCardView.getCardBackgroundColor().getDefaultColor());
                        theDataList.add(new TheData(colour));
//                        theDataArrayList1.add(theData);
                        Log.d("dataa", theDataList.get(j).getColourCodesList());*/
                        /*Log.d("dataa", colourList.get(j).getColourCode());
                    }*/
//                }
//                ColourVariantRecyclerViewAdapter colourVariantRecyclerViewAdapter = new ColourVariantRecyclerViewAdapter();
                /*colourList = productVariantRecyclerViewAdapter.getColourList();
                colourSize = productVariantRecyclerViewAdapter.getColourListSize();
                int myListSize = productVariantRecyclerViewAdapter.getItemCount();
                variantRecyclerView.setItemViewCacheSize(myList.size());

                List<VariantData> theDataArrayList1 = new ArrayList<>();
//                List<DataExtraction> variantDataArrayList = new ArrayList<>();
                List<TheData> theDataList = new ArrayList<>();
                VariantData theData = new VariantData();

                for (int i = 0; i < myList.size(); i++) {
                    View variantRecyclerViewView = variantRecyclerView.getChildAt(i);
                    EditText nameEditText = variantRecyclerViewView.findViewById(R.id.variant_name);
                    String name = nameEditText.getText().toString();

//                    colourVariantRecyclerView.setItemViewCacheSize(colourList.size());
                    theData.setSizeName(name);
                    theDataArrayList1.add(theData);
                    Log.d("dataa", theDataArrayList1.get(i).getSizeName());

                    for (int j = 0; j < colourSize; j++) {
                        RecyclerView colourVariantRecyclerView = variantRecyclerViewView.findViewById(R.id.colour_variant_recyclerview);
                        View colourVariantRecyclerViewView = colourVariantRecyclerView.getChildAt(j);
                        MaterialCardView materialCardView = colourVariantRecyclerView.findViewById(R.id.colour_cardview);
//                        theData.setColourCodes(colourList).get(j).getColourCode();
                        String colour = String.valueOf(materialCardView.getCardBackgroundColor().getDefaultColor());
                        theDataList.add(new TheData(colour));
//                        theDataArrayList1.add(theData);
                        Log.d("dataa", theDataList.get(j).getColourCodesList());
                    }*/
//                    variantRecyclerView.getLayoutManager().getChildCount();
                    /*for (int j = 0; j< colourSize;j++){
                        MaterialCardView materialCardView = variantRecyclerViewView.findViewById(R.id.colour_cardview);
//                        String colour = String.valueOf(materialCardView.getCardBackgroundColor().getDefaultColor());
                        theData.setColourCodesList(colourList);
                        theDataList.add(theData);
                        Log.d("dataa", theDataList.get(i).getColourCodesList().get(j).getColourCode());
                    }*/
                //}
//Working
                /*for (int i = 0; i < myList.size(); i++) {
                    Log.d("dataa", theDataList.get(i).getSizeName());
                    for (int j = 0; j < colourSize; j++) {
//                    MaterialCardView materialCardView = variantRecyclerViewView.findViewById(R.id.colour_cardview);
////                        String colour = String.valueOf(materialCardView.getCardBackgroundColor().getDefaultColor());
                        theData.setColourCodesList(colourList);
                        theDataList.add(theData);
                        Log.d("dataa", theDataList.get(i).getColourCodesList().get(j).getColourCode());
                    }
                }*/
//                variantRecyclerView.setItemViewCacheSize(colourList.size());

//                List<TheData> theDataList = new ArrayList<>();

/*                for (int i = 0; i< theDataList.size(); i++){
                    Log.d("dataa", theDataList.get(i).getSizeName());

//                    Log.d("dataa", theDataArrayList1.get(i).getPrice());
//                    Log.d("dataa", theDataArrayList1.get(i).getDiscountedPrice());
                    Log.d("dataa", theDataList.get(i).getSizeName());
//                    Log.d("dataa", myList.get(i).getData().getColourCode());
//                    Log.d("dataa", myList.get(i).getData().getColourName());
                    //if (colourList.size() > 0) {
                        for (int j = 0; j < colourSize; j++) {
                            Log.d("dataa", theDataList.get(i).getColourCodesList().get(j).getColourCode());
//                    Log.d("dataa", theDataArrayList1.get(i).getPrice());
//                    Log.d("dataa", theDataArrayList1.get(i).getDiscountedPrice());
//                            Log.d("dataa", theDataArrayList1.get(i).getSizeName());
//                    Log.d("dataa", myList.get(i).getData().getColourCode());
//                    Log.d("dataa", myList.get(i).getData().getColourName());
                        }
                    //}
                }*/
//                for (int i = 0; i< variantDataArrayList.size(); i++){
//                    Log.d("dataa", variantDataArrayList.get(i).getColourCodes().getColourCode());
////                    Log.d("dataa", variantDataArrayList.get(i).getPrice());
////                    Log.d("dataa", variantDataArrayList.get(i).getDiscountedPrice());
////                            Log.d("dataa", variantDataArrayList.get(i).getSizeName());
////                    Log.d("dataa", myList.get(i).getData().getColourCode());
////                    Log.d("dataa", myList.get(i).getData().getColourName());
//                }
            });
            bottomSheetDialog.show();
        });

        add_product_button.setOnClickListener
                (v ->
                {
                    View view2 = getLayoutInflater().inflate(R.layout.home_fragment_added_success, null);
                    BottomSheetDialog bottomSheetDialog1 = new BottomSheetDialog(v.getContext());
                    bottomSheetDialog1.setContentView(view2);
                    bottomSheetDialog1.show();
                });


        //MAAZ END
























    recyclerView = findViewById(R.id.prod_images_recyclerView);
        AddImages = findViewById(R.id.add_product_images);
        AddVarient = findViewById(R.id.add_new_product_variant_button);
        AddProduct = findViewById(R.id.add_product_button);
        ProdPrice = findViewById(R.id.prod_price);
        ProdName = findViewById(R.id.prod_name);
        ProdCategory = findViewById(R.id.prod_category);
        ProdPrice = findViewById(R.id.prod_price);
        ProdPrice = findViewById(R.id.prod_price);
        ProdDiscount = findViewById(R.id.prod_discount);
        ProdDetails = findViewById(R.id.prod_details);
        ProdMeasure = findViewById(R.id.prod_measure);
        NumberofProd = findViewById(R.id.number_of_prod);



        imageAdapter = new imageAdapter(Images);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(imageAdapter);

        Intent intent = getIntent();
        String Key = intent.getStringExtra("key");



        databaseReference = FirebaseDatabase.getInstance().getReference("Products").child(Key);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if(snapshot.exists())
                {

                    //String Number_of_product = String.valueOf(aboutModelClass.getNumber_of_Units());
                    productNameModelClass = snapshot.getValue(ProductNameModelClass.class);

                    Log.i("checknamelist", productNameModelClass.getName());
                    ProdName.setText(productNameModelClass.getName());
                    ProdCategory.setText(productNameModelClass.getCategory());
                    ProdPrice.setText(String.valueOf(productNameModelClass.getPrice()));
                    ProdMeasure.setText(productNameModelClass.getUnit_measure());
                    ProdDetails.setText(productNameModelClass.getDetails());
                    ProdDiscount.setText(String.valueOf(productNameModelClass.getDiscount_price()));
                    NumberofProd.setText(String.valueOf(productNameModelClass.getNumber_of_units()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        Bundle intent = getIntent().getExtras();
//        ProductName = intent.get("name").toString();
//        ProductCategory = intent.get("category").toString();


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

        ProdCategory.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
//                new_product_category addcatsheet = new new_product_category();

                addCategoryDialog = new BottomSheetDialog(v.getContext(), R.style.CustomAlertDialog);
                addCategoryDialog.setContentView(R.layout.home_fragment_new_product_category);
                addCategoryDialog.show();
                RecyclerView categoriesRecyclerView = addCategoryDialog.findViewById(R.id.rvCategories);
                 MaterialButton addNewCategory = addCategoryDialog.findViewById(R.id.add_new_product_category_button);
                RecyclerAdapter adapter = new RecyclerAdapter(categories);
                categoriesRecyclerView.setAdapter(adapter);
                categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));

                addNewCategory.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        BottomSheetDialog addNewCatSheet = new BottomSheetDialog(view.getContext(),
                                R.style.CustomAlertDialog);
                        addNewCatSheet.setContentView(R.layout.productsncategories_fragment_add_category);
                        addNewCatSheet.show();
                    }
                });
            }
        });

        AddProduct.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String Name = ProdName.getText().toString();
                String Category = ProdCategory.getText().toString();
                String Measure = ProdMeasure.getText().toString();
                String Number_of_Units = NumberofProd.getText().toString();
                String Price = ProdPrice.getText().toString();
                String Discount_Price = ProdDiscount.getText().toString();
                String Details = ProdDetails.getText().toString();

                ProductModelClass productModelClass = new ProductModelClass(null, Name, Measure, Integer.parseInt(Discount_Price)
                        , Integer.parseInt(Number_of_Units), Integer.parseInt(Price));


                AboutModelClass aboutModelClass = new AboutModelClass(Category, Details, Name,Measure, Integer.parseInt(Discount_Price),
                        Integer.parseInt(Number_of_Units), Integer.parseInt(Price), null);

                uploadImage(Images, productModelClass, aboutModelClass);



            }
        });



    }


    //MAAZ START
    @Override
    public void OnRemoveClick(int index)
    {
        myList.remove(index);
//        productVariantRecyclerViewAdapter.notifyData(myList);
    }

    @Override
    public void OnColourRemoveClick(int index)
    {
        Log.d("III", "I reached here");
        //colourSize = index;
    }

    /*@Override
    public void getC(List<ColourCodes> colourData) {

    }*/

    /*@Override
    public void getCLSize(int size) {

    }*/

    @Override
    public void getV(List<VariantData> variantDataList)
    {
        myList = variantDataList;
    }

    @Override
    public void getVLSize(int size)
    {
        variantSize = size;
    }

    @Override
    public void getVC(List<ColourCodes> colourData)
    {
        colourList = colourData;
    }

    @Override
    public void getVCLSize(int size)
    {
        //colourSize = size;
    }

    /*@Override
    public void getC(List<ColourData> colourListL) {
        colourList = colourListL;
    }*/

    /*@Override
    public void getC(List<ColourData> colourData) {
        colourList = colourData;
    }

    @Override
    public void getCLSize(int colourSizeL) {
        colourSize = colourSizeL;
    }

    @Override
    public void getV(List<VariantData> variantDataList) {
        myList = variantDataList;
    }

    @Override
    public void getVLSize(int variantSizeL) {
        variantSize = variantSizeL;
    }*/

    /*@Override
    public List<ColourData> getC() {
        return null;
    }

    @Override
    public int getCLSize() {
        return 0;
    }

    @Override
    public List<VariantData> getV() {
        return null;
    }

    @Override
    public int getVLSize() {
        return 0;
    }*/

    //MAAZ END













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

    public class RecyclerAdapter extends
            RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        // Provide a direct reference to each of the views within a data item
        // Used to cache the views within the item layout for fast access
        public  class ViewHolder extends RecyclerView.ViewHolder {
            // Your holder should contain a member variable
            // for any view that will be set as you render a row
            public MaterialCheckBox checkBox;
            // We also create a constructor that accepts the entire item row
            // and does the view lookups to find each subview
            public ViewHolder(View itemView) {
                // Stores the itemView in a public final member variable that can be used
                // to access the context from any ViewHolder instance.
                super(itemView);
                checkBox = (MaterialCheckBox) itemView.findViewById(R.id.material_check_box) ;

            }
        }

        @Override
        public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            // Inflate the custom layout
            View ordersView = inflater.inflate(R.layout.home_new_category_tile, parent, false);

            // Return a new holder instance
            RecyclerAdapter.ViewHolder viewHolder = new RecyclerAdapter.ViewHolder(ordersView);
            return viewHolder;
        }
        private List<String> categoriesList;

        public RecyclerAdapter(Set<String> category) {
            categoriesList = new ArrayList<>(category);
        }

        // Involves populating data into the item through holder
        @Override
        public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
            // Get the data model based on position
            String category = categoriesList.get(position);

            // Set item views based on your views and data model
            MaterialCheckBox checkBoxText = holder.checkBox;
            checkBoxText.setText(category);


        }

        // Returns the total count of items in the list
        @Override
        public int getItemCount() {
            return categoriesList.size();
        }
    }

    public void getCategories(){

        productRef.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task1) {
                        if (task1.isSuccessful()) {
                            for (QueryDocumentSnapshot documentSnap : task1.getResult()) {
                                productRef.document(documentSnap.getId()).collection("about")
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task2) {
                                                if(task2.isSuccessful()){
                                                    for(QueryDocumentSnapshot document : task2.getResult()){
//                                                        Log.d(TAG, "Error getting documents: "+ document.getData().get("Category"));
                                                        categories.add(document.getData().get("Category").toString());
                                                    }
                                                }
                                            }
                                        });
                                Log.d(TAG, documentSnap.getId() + " => " + documentSnap.getData());
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task1.getException());
                        }
                    }
                });



    }

//    public void setListener(){
//        db.collection("cities")
//                .whereEqualTo("state", "CA")
//                .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable QuerySnapshot value,
//                                        @Nullable FirebaseFirestoreException e) {
//                        if (e != null) {
//                            Log.w(TAG, "Listen failed.", e);
//                            return;
//                        }
//
//                        List<String> cities = new ArrayList<>();
//                        for (QueryDocumentSnapshot doc : value) {
//                            if (doc.get("name") != null) {
//                                cities.add(doc.getString("name"));
//                            }
//                        }
//                        Log.d(TAG, "Current cites in CA: " + cities);
//                    }
//                });
//    }



}