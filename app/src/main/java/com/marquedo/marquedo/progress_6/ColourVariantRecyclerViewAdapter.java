package com.marquedo.marquedo.progress_6;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import com.marquedo.marquedo.R;

import java.util.ArrayList;
import java.util.List;

public class ColourVariantRecyclerViewAdapter extends RecyclerView.Adapter<ColourVariantRecyclerViewAdapter.ViewHolder> {

    private Context contextG;
    public List<ColourCodes> colourList;
    public List<TheData> theDataArrayList;
    int mLastPosition = 0;
    public GetColourVariants getColourVariants;
    public int variantPosition;
    List<VariantData> myList;

    public ColourVariantRecyclerViewAdapter(List<VariantData> myList, List<ColourCodes> colourList, int position, Context contextG, GetColourVariants getColourVariants) {
        this.contextG = contextG;
        this.myList = myList;
        this.getColourVariants = getColourVariants;
//        this.theDataArrayList = theDataArrayList;
        this.variantPosition = position;
        this.colourList = colourList;
        Log.d("da", "ColourConstructorVarPos" + variantPosition);
    }

    /*@Override
    public List<ColourData> getC() {
        return colourList;
    }

    @Override
    public int getCLSize() {
        return getItemCount();
    }*/

    /*public ColourVariantRecyclerViewAdapter() {

    }*/

    /*public ColourVariantRecyclerViewAdapter(Context newProductDetails) {
        this.contextG = newProductDetails;
    }*/

    /*public ColourVariantRecyclerViewAdapter() {

    }*/

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final MaterialTextView delete;
        private final MaterialCardView colourCardView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            delete = itemView.findViewById(R.id.delete);
            colourCardView = itemView.findViewById(R.id.colour_cardview);
        }
    }
    /*public ColourVariantRecyclerViewAdapter(List<ColourCodes> colourList, int position, Context context, GetColourVariants getColourVariants) {
        this.contextG = context;
        this.colourList = colourList;
        this.getColourVariants = getColourVariants;
//        this.theDataArrayList = theDataArrayList;
        this.variantPosition = position;
    }*/

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.progress_6_colour_variant_recyclerview, viewGroup, false);

        return new ColourVariantRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Log.d("da", "ColourOnBindVarPos" + variantPosition);
        Log.d("da", "ColourOnBindPos" + position);
        ColourCodes colour = myList.get(variantPosition).getColourCodes().get(position);
        /*if (colour.length() == 5)
            colour = "#000" + colour;
        else if (colour.length() == 7)
            colour = "#0" + colour;
        else if (colour.length() == 8)
            colour = "#" + colour;
        else
            colour= "#" + colour;*/
//        Log.d("PVRVA", colour.trim());
//        theDataArrayList.add(new TheData(colourList));
        viewHolder.colourCardView.setCardBackgroundColor(Integer.parseInt(colour.getColourCode()));
        viewHolder.delete.setOnClickListener(view -> {
//            colourList.remove(position);
            myList.get(variantPosition).getColourCodes().remove(position);
            //theDataArrayList.remove(position);
            notifyItemRemoved(position);
            notifyItemChanged(position);
        });
        mLastPosition = viewHolder.getAdapterPosition();
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return colourList.size();
    }

    public void notifyColourData(int position, List<VariantData> myList, List<ColourCodes> colourList) {
        Log.d("notifyData ", this.colourList.size() + "");
        this.myList = myList;
        this.variantPosition = position;
        notifyItemChanged(mLastPosition);
        this.colourList = colourList;
        Log.d("da", "ColourNotifyVarPos" + variantPosition);
    }

public int getColourListSize() {
        return colourList.size();
}

    public List<ColourCodes> getColourList() {
        return colourList;
    }

    /*public List<ColourData> getC() {
        return colourList;
    }

    public int getCLSize() {
        return colourList.size();
    }*/

    /*public void getVarC(){
        getColourVariants.getC(colourList);
        getColourVariants.getCLSize(getItemCount());
    }*/

    /*public List<ColourCodes> getCC(){
        return colourList;
    }*/

    /*public List<ColourData> getMCC() {
        return colourList;
    }*/

    /*public void getMCC() {
        getColourVariants.getC(colourList);
        getColourVariants.getCLSize(colourList.size());
    }*/

    public int getCCLSize() {
        return colourList.size();
    }
}
