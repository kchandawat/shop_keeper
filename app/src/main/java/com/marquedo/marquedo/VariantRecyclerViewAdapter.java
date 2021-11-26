package com.marquedo.marquedo;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;
import java.util.List;

import eltos.simpledialogfragment.color.SimpleColorDialog;

public class VariantRecyclerViewAdapter extends RecyclerView.Adapter<VariantRecyclerViewAdapter.ViewHolder> {

    private final Context contextG;
    private FragmentActivity fragmentActivity;
    private List<String> localDataSet;

    /**
         * Provide a reference to the type of views that you are using
         * (custom ViewHolder).
         */
        public static class ViewHolder extends RecyclerView.ViewHolder {
            private final MaterialButton new_color;
            private final EditText sizeVariantName;
            private final EditText variantPrice;
            private final EditText variantDiscountedPrice;
            private final ExpandableLayout expandableLayout1;
            private final TextView dropdown2;

            public ViewHolder(View view) {
                super(view);
                // Define click listener for the ViewHolder's View

                sizeVariantName = itemView.findViewById(R.id.size_variant_name);
                variantPrice = itemView.findViewById(R.id.variant_price);
                variantDiscountedPrice = itemView.findViewById(R.id.variant_discounted_price);
                new_color = itemView.findViewById(R.id.add_new_color_variant_button);
                expandableLayout1 = itemView.findViewById(R.id.expandable_layout_1);
                dropdown2 = itemView.findViewById(R.id.add_color_variant);
            }

            /*public TextView getTextView() {
                return textView;
            }*/
        }

        /**
         * Initialize the dataset of the Adapter.
         *
         */
        public VariantRecyclerViewAdapter(Context context, FragmentActivity fragmentActivity) {
            this.contextG = context;
            this.fragmentActivity = fragmentActivity;
            localDataSet = new ArrayList<>();
        }

        // Create new views (invoked by the layout manager)
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            // Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.product_variant_recyclerview, viewGroup, false);

            return new ViewHolder(view);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {

            // Get element from your dataset at this position and replace the
            // contents of the view with that element

//            String blogListItem = blogListItemsG.get(position);

            String sizeVariantNameString = viewHolder.sizeVariantName.getText().toString();
            String variantPriceString = viewHolder.variantPrice.getText().toString();
            String variantDiscountedPriceString = viewHolder.variantDiscountedPrice.getText().toString();

            viewHolder.dropdown2.setOnClickListener(v -> viewHolder.expandableLayout1.toggle());

            SimpleColorDialog simpleColorDialog = SimpleColorDialog.build()
                    .title("pick_a_color")
                    .colorPreset(Color.RED);

            viewHolder.new_color.setOnClickListener(v -> simpleColorDialog.show(fragmentActivity, "dialogTagColor"));
            int color = simpleColorDialog.getExtras().getInt(SimpleColorDialog.COLOR);
            Toast.makeText(contextG, color, Toast.LENGTH_LONG).show();
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return localDataSet.size();
        }
    }