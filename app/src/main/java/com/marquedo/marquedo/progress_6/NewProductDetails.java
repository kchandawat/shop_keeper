package com.marquedo.marquedo.progress_6;

import static com.marquedo.marquedo.helpers.OurConstants.SHOW_TIME;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.marquedo.marquedo.R;
import com.marquedo.marquedo.datab.Variant;
import com.marquedo.marquedo.databinding.Progress6ProductVariantRecyclerviewBinding;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NewProductDetails extends AppCompatActivity implements RemoveClickListner, RemoveColourClickListner, GetVariants {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.progress_6_new_product_details);
        MaterialButton addProductVariant = findViewById(R.id.add_new_product_variant_button);
        Button add_product_button = findViewById(R.id.add_product_button);
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

        addProductVariant.setOnClickListener(view -> {
            View view1=getLayoutInflater().inflate(R.layout.progress_6_variant_card,null);
            BottomSheetDialog bottomSheetDialog= new BottomSheetDialog(this);
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
            ExpandableLayout expandableLayout = view1.findViewById(R.id.expandable_layout);


            /*Log.d("PVAdap", "Main" + variantData.getSizeName());
            Log.d("PVAdap", "Main" + variantData.getPrice());
            Log.d("PVAdap", "Main" + variantData.getDiscountedPrice());
            Log.d("PVAdap", "Main" + myList.toString());
            productVariantRecyclerViewAdapter.notifyData(myList);*/

            TextView dropdown1 =  view1.findViewById(R.id.add_size_variant);

            EditText sizeVariantName = view1.findViewById(R.id.variant_name);
            EditText variantPrice = view1.findViewById(R.id.variant_price);
            EditText variantDiscountedPrice = view1.findViewById(R.id.variant_discounted_price);
            MaterialButton new_color = view1.findViewById(R.id.add_new_color_variant_button);
            ExpandableLayout expandableLayout1 = view1.findViewById(R.id.expandable_layout_1);
            TextView dropdown2 = view1.findViewById(R.id.add_color_variant);

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

        add_product_button.setOnClickListener(v -> {
            View view2 = getLayoutInflater().inflate(R.layout.product_added_success,null);
            BottomSheetDialog bottomSheetDialog1 = new BottomSheetDialog(v.getContext());
            bottomSheetDialog1.setContentView(view2);
            bottomSheetDialog1.show();
        });
    }

    @Override
    public void OnRemoveClick(int index) {
        myList.remove(index);
//        productVariantRecyclerViewAdapter.notifyData(myList);
    }

    @Override
    public void OnColourRemoveClick(int index) {
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
    public void getV(List<VariantData> variantDataList) {
        myList = variantDataList;
    }

    @Override
    public void getVLSize(int size) {
        variantSize = size;
    }

    @Override
    public void getVC(List<ColourCodes> colourData) {
        colourList = colourData;
    }

    @Override
    public void getVCLSize(int size) {
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
}