package com.marquedo.marquedo.AddProductVariant;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.marquedo.marquedo.BR;
import com.marquedo.marquedo.R;
import com.marquedo.marquedo.datab.Variant;
import com.marquedo.marquedo.databinding.Progress6ProductVariantRecyclerviewBinding;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.List;

import yuku.ambilwarna.AmbilWarnaDialog;

public class ProductVariantRecyclerViewAdapter extends RecyclerView.Adapter<ProductVariantRecyclerViewAdapter.MyViewHolder> {

    private static final String COLOUR_DIALOG_CODE = "ColourTag";
    private final Context contextG;
    private List<VariantData> myList;
    int mLastPosition = 0;
    private List<ColourCodes> colourList;
    private ColourVariantRecyclerViewAdapter colourVariantRecyclerViewAdapter;
    private int mDefaultColor;
    private List<TheData> theDataArrayList;
    public GetVariants getVariants;
    public GetColourVariants getColourVariants;
    private ArrayList<Variant> prodList;
    private int colourSize;
    List<ColourCodes> colourDataList;

    /*@Override
    public void getC(List<ColourCodes> colourData) {
        colourList = colourData;
    }

    @Override
    public void getCLSize(int size) {
        colourSize = size;
    }*/

    /*@Override
    public List<VariantData> getV() {
        return myList;
    }

    @Override
    public int getVLSize() {
        return getItemCount();
    }

    @Override
    public void getV(List<VariantData> variantDataList) {

    }

    @Override
    public void getVLSize(int size) {

    }*/

    /*@Override
    public boolean onResult(@NonNull String dialogTag, int which, @NonNull Bundle extras) {
        Log.d("PVRVA", String.valueOf(extras.getInt(SimpleColorDialog.COLOR)));
        if (COLOUR_DIALOG_CODE.equals(dialogTag) && which == BUTTON_POSITIVE){
            int colour = extras.getInt(SimpleColorDialog.COLOR);
            if (colourRecyclerView.getAdapter() == null) {
                colourList = new ArrayList<>();
                colourList.add(String.valueOf(colour));
                Log.d("PVRVA", String.valueOf(colour));

                //Create adapter
                colourVariantRecyclerViewAdapter = new ColourVariantRecyclerViewAdapter(colourList, mListner, contextG);

                colourRecyclerView.setAdapter(colourVariantRecyclerViewAdapter);
                addColourToVariant.setText(contextG.getResources().getString(R.string.add_more_colours));
            } else {
                colourList.add(String.valueOf(colour));
                colourVariantRecyclerViewAdapter.notifyColourData(colourList);
            }
            return true;
        }
        // ...
        return false;
    }*/

    /**
         * Provide a reference to the type of views that you are using
         * (custom ViewHolder).
         */
        public static class MyViewHolder extends RecyclerView.ViewHolder {
            private final ExtendedFloatingActionButton addColourToVariant;
            private final EditText sizeVariantName;
            private final EditText variantPrice;
            private final EditText variantDiscountedPrice;
           // private final ExpandableLayout expandableLayout1;
           // private final TextView dropdown2;
            private final TextView addSizeVariant;
            private final ConstraintLayout variantConstraintLayout;
            private final MaterialCardView expandableCardView;
            private final RecyclerView colourRecyclerView;
            private final AppCompatImageView deleteVariant;
            private Progress6ProductVariantRecyclerviewBinding binding;
            private GetVariants getVariants;
            private GetColourVariants getColourVariants;

        public MyViewHolder(Progress6ProductVariantRecyclerviewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            sizeVariantName = itemView.findViewById(R.id.variant_name);
            variantPrice = itemView.findViewById(R.id.variant_price);
            variantDiscountedPrice = itemView.findViewById(R.id.variant_discounted_price);
            addColourToVariant = itemView.findViewById(R.id.add_new_color_variant_button);
            //expandableLayout1 = itemView.findViewById(R.id.expandableCardView);
            //dropdown2 = itemView.findViewById(R.id.add_color_variant);
            variantConstraintLayout = itemView.findViewById(R.id.variant_details);
            expandableCardView = itemView.findViewById(R.id.expandableCardView);
            addSizeVariant = itemView.findViewById(R.id.add_size_variant);
            colourRecyclerView = itemView.findViewById(R.id.colour_variant_recyclerview);
            deleteVariant = itemView.findViewById(R.id.delete_variant);
        }
        public void bind(Variant obj) {
            binding.setVariable(BR.variant,obj);
            binding.executePendingBindings();
        }

            /*public ViewHolder(View view) {
                super(view);
                // Define click listener for the ViewHolder's View

                sizeVariantName = itemView.findViewById(R.id.variant_name);
                variantPrice = itemView.findViewById(R.id.variant_price);
                variantDiscountedPrice = itemView.findViewById(R.id.variant_discounted_price);
                addColourToVariant = itemView.findViewById(R.id.add_new_color_variant_button);
                expandableLayout1 = itemView.findViewById(R.id.expandable_layout_1);
                dropdown2 = itemView.findViewById(R.id.add_color_variant);
                variantConstraintLayout = itemView.findViewById(R.id.variant_details);
                expandableCardView = itemView.findViewById(R.id.expandableCardView);
                addSizeVariant = itemView.findViewById(R.id.add_size_variant);
                colourRecyclerView = itemView.findViewById(R.id.colour_variant_recyclerview);
                deleteVariant = itemView.findViewById(R.id.delete_variant);
            }*/

            /*public TextView getTextView() {
                return textView;
            }*/
        }

        /**
         * Initialize the dataset of the Adapter.
         *
         */
        public ProductVariantRecyclerViewAdapter(List<VariantData> myList, ArrayList<Variant> prodList, Context context,
                                                 GetVariants getVariants) {
            this.contextG = context;
            this.myList = myList;
            this.prodList = prodList;
            mDefaultColor = 0;
//            this.theDataArrayList = theDataArrayList;
//            colourList = colourDataArrayList;
            theDataArrayList = new ArrayList<>();
            this.getVariants = getVariants;
//            this.getColourVariants = getColourVariants;
            colourDataList = new ArrayList<>();
        }

        // Create new views (invoked by the layout manager)
        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            // Create a new view, which defines the UI of the list item
            LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
            Progress6ProductVariantRecyclerviewBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.progress_6_product_variant_recyclerview, viewGroup, false);

            return new MyViewHolder(binding);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(MyViewHolder viewHolder, final int position) {

            // Get element from your dataset at this position and replace the
            // contents of the view with that element

            /*viewHolder.sizeVariantName.setText(myList.get(position).getSizeName());
            viewHolder.variantPrice.setText(myList.get(position).getPrice());
            viewHolder.variantDiscountedPrice.setText(myList.get(position).getDiscountedPrice());*/

/*
            viewHolder.sizeVariantName.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    */
/*TheData s = new TheData(charSequence.toString());
                    theDataArrayList.add(s);*//*

                    TheData theData = new TheData();
                    theData.setSizeName(charSequence.toString());
                    theDataArrayList.add(theData);
                }

                @Override
                public void afterTextChanged(Editable editable) {
                    */
/*TheData s = new TheData(editable.toString());
                    theDataArrayList.add(s);*//*

                    TheData theData = new TheData();
                    theData.setSizeName(editable.toString());
                    theDataArrayList.add(theData);
                }
            });
*/
//            viewHolder.bind(prodList.get(position));
            mLastPosition = viewHolder.getAdapterPosition();
            colourList = new ArrayList<>();
            /*Log.d("PVAdap", myList.get(position).getSizeName());
            Log.d("PVAdap", myList.get(position).getPrice());
            Log.d("PVAdap", myList.get(position).getDiscountedPrice());*/

//            viewHolder.dropdown2.setOnClickListener(v -> viewHolder.expandableLayout1.toggle());

            viewHolder.addSizeVariant.setOnClickListener(view -> {
                if (viewHolder.variantConstraintLayout.getVisibility() == View.VISIBLE) {
                    // The transition of the hiddenView is carried out
                    //  by the TransitionManager class.
                    // Here we use an object of the AutoTransition
                    // Class to create a default transition.
                    TransitionManager.beginDelayedTransition(viewHolder.expandableCardView,
                            new AutoTransition());
                    viewHolder.variantConstraintLayout.setVisibility(View.GONE);
                    viewHolder.addSizeVariant.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_arrow_down_24, 0);
                    // TO DO recyclerview Position to bottom
                }

                // If the CardView is not expanded, set its visibility
                // to visible and change the expand more icon to expand less.
                else {
                    TransitionManager.beginDelayedTransition(viewHolder.expandableCardView,
                            new AutoTransition());
                    viewHolder.variantConstraintLayout.setVisibility(View.VISIBLE);
                    viewHolder.addSizeVariant.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_keyboard_arrow_up_24, 0);
                    // TO DO recyclerview Position to bottom
                }
            });

            Log.d("da", "viewHolder.getAdap" + viewHolder.getAdapterPosition());
            Log.d("da", "pos" + position);
                viewHolder.addColourToVariant.setOnClickListener(v ->
                        openColorPickerDialogue(viewHolder.colourRecyclerView, viewHolder.addColourToVariant, viewHolder.getAdapterPosition()));

            viewHolder.deleteVariant.setOnClickListener(view -> {
                if (myList.size() > 1){
                    myList.remove(position);
//                    theDataArrayList.remove(position);
                    notifyItemRemoved(position);
                    notifyItemChanged(position);
                }
            });
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return myList.size();
        }

    public void openColorPickerDialogue(RecyclerView colourRecyclerView, ExtendedFloatingActionButton addColourToVariant, int position) {

        Log.d("da", "OCPD Pos" + position);
        // the AmbilWarnaDialog callback needs 3 parameters
        // one is the context, second is default color,
        final AmbilWarnaDialog colorPickerDialogue = new AmbilWarnaDialog(contextG, mDefaultColor,
                new AmbilWarnaDialog.OnAmbilWarnaListener() {
                    @Override
                    public void onCancel(AmbilWarnaDialog dialog) {
                        // leave this function body as
                        // blank, as the dialog
                        // automatically closes when
                        // clicked on cancel button
                    }

                    @Override
                    public void onOk(AmbilWarnaDialog dialog, int color) {
                        // change the mDefaultColor to
                        // change the GFG text color as
                        // it is returned when the OK
                        // button is clicked from the
                        // color picker dialog
                        mDefaultColor = color;

                        String colour = String.valueOf(color);
                        Log.d("PVRVA", colour);
                        // now change the picked color
                        // preview box to mDefaultColor
                        ColourData colourData = new ColourData();
                        VariantData variantData = new VariantData();
//                        ColourCodes colourCodes = new ColourCodes();
                        if (colourRecyclerView.getAdapter() == null) {

                            /*ArrayList<ArrayList<ColourCodes>> v = new ArrayList<ArrayList<ColourCodes>>();
                            ArrayList<ColourCodes> variantArrayList = new ArrayList<>();
                            v.add(variantArrayList);
                            colourCodes.setColourCode(colour);
//                            variantData.setColourCodes(colourCodes);
                            variantArrayList.add(colourCodes);*/
                            /*ArrayList<ArrayList<ColourCodes>> v = new ArrayList<>();
                            ArrayList<ColourCodes> variantArrayList = new ArrayList<>();
                            v.add(variantArrayList);
                            colourCodes.setColourCode(colour);
                            variantData.setColourCodes(v);
                            variantArrayList.add(colourCodes);
                            myList.add(variantData);*/
                            /*colourCodes.setColourCode(colour);
                            colourDataList.add(colourCodes);
                            colourData.setColourCodesList(colourDataList);
                            colourList.add(colourData);*/
//                            colourData.setColourCodesList(colour);
//                            colourData.setColourCodesList(colourList);
                            //theDataArrayList.add(colourData);
                            //colourData.setColourCodesList(colourList);
                            //theDataArrayList.add(new TheData(colourList));
                            colourList.add(new ColourCodes(colour, ""));
                            myList.get(position).setColourCodes(colourList);
                            Log.d("PVRVA", colour);

                            //Create adapter
                            colourVariantRecyclerViewAdapter = new ColourVariantRecyclerViewAdapter(myList, colourList, position, contextG, getColourVariants);

                            colourRecyclerView.setAdapter(colourVariantRecyclerViewAdapter);
                            addColourToVariant.setText(contextG.getResources().getString(R.string.add_more_colours));
                        } else {
                            /*colourCodes.setColourCode(colour);
                            colourList.add(colourCodes);
                            colourData.setColourCodesList(colourList);
                            colourDataList.add(colourData);*/

                            /*ArrayList<ArrayList<ColourCodes>> v = new ArrayList<ArrayList<ColourCodes>>();
                            ArrayList<ColourCodes> variantArrayList = new ArrayList<>();
                            v.add(variantArrayList);
                            colourCodes.setColourCode(colour);
//                            variantData.setColourCodes(colourCodes);
                            variantArrayList.add(colourCodes);*/
                            /*ArrayList<ArrayList<ColourCodes>> v = new ArrayList<>();
                            ArrayList<ColourCodes> variantArrayList = new ArrayList<>();
                            v.add(variantArrayList);
                            colourCodes.setColourCode(colour);
                            variantData.setColourCodes(v);
                            variantArrayList.add(colourCodes);
                            myList.add(variantData);*/
                            /*colourCodes.setColourCode(colour);
                            colourDataList.add(colourCodes);
                            colourData.setColourCodesList(colourDataList);
                            colourList.add(colourData);*/
//                            colourDataList.get(mLastPosition).setColourCodesList(colourList);
//                            colourData.setColourCodesList(colour);
//                            colourData.setColourCodesList(colourList);
                            //theDataArrayList.add(colourData);
                            //colourData.setColourCodesList(colourList);
                            colourList.add(new ColourCodes(colour, ""));
                            myList.get(position).setColourCodes(colourList);
                            colourVariantRecyclerViewAdapter.notifyColourData(position, myList, colourList);
                        }

//                        colourRecyclerView.setItemViewCacheSize(colourList.size());

                        List<VariantData> variantDataArrayList = new ArrayList<>();

                        /*for (int i = 0; i < colourList.size(); i++) {
                            View variantRecyclerViewView = colourRecyclerView.getChildAt(i);
                            if (variantRecyclerViewView != null) {
                                MaterialCardView nameEditText = variantRecyclerViewView.findViewById(R.id.colour_cardview);
                                String name = nameEditText.getCardBackgroundColor().toString();

                                VariantData variantData = new VariantData();
                                variantData.getColourCodes().setColourCode(colour);
                                variantDataArrayList.add(variantData);
                            }
                        }*/

                        /*if (variantDataArrayList.size() >0) {
                            for (int i = 0; i < variantDataArrayList.size(); i++) {
                                Log.d("dataa", variantDataArrayList.get(i).getColourCodes().getColourCode());
                            }
                        }*/
                    }
                });
        colorPickerDialogue.show();
    }

    public void notifyData(List<VariantData> myList) {
        Log.d("notifyData ", myList.size() + "");
        this.myList = myList;
        notifyItemChanged(mLastPosition);
    }

    /*public void getVar(){
            getVariants.getV(myList);
            getVariants.getVLSize(getItemCount());
    }*/

    /*public void getVV() {
        getVariants.getV(myList);
        getVariants.getVLSize(getItemCount());
    }*/

    public int getVVLSize() {
            return getItemCount();
    }

    /*public List<VariantData> getMVV() {
        return myList;
    }

    public List<ColourData> getCCC() {
        return colourVariantRecyclerViewAdapter.getMCC();
    }*/

    public void getMVV() {
        if (myList != null) {
//            Log.d("dataa", myList.get(1).getSizeName());
            getVariants.getV(myList);
            getVariants.getVLSize(myList.size());
            getVariants.getVC(colourList);
            getVariants.getVCLSize(colourList.size());
        }
    }

    /*public void getMCC() {
        getColourVariants.getC(colourList);
        getColourVariants.getCLSize(colourList.size());
    }*/

    /*public void getCCC() {
        colourVariantRecyclerViewAdapter.getMCC();
    }*/

    /*public void getCC(){
        colourVariantRecyclerViewAdapter.getVarC();
    }

    public int getCCLSize() {
        return colourVariantRecyclerViewAdapter.getItemCount();
    }*/
}