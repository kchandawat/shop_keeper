package com.marquedo.marquedo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.checkbox.MaterialCheckBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoriesRecyclerViewAdapter extends
        RecyclerView.Adapter<CategoriesRecyclerViewAdapter.ViewHolder> {

    private List<String> blogListItemsG;
    private final Context contextG;
    private CheckboxData checkboxData;

    public CategoriesRecyclerViewAdapter(List<String> blogListItems, Context context, CheckboxData checkboxData) {
        this.blogListItemsG = blogListItems;
        this.contextG = context;
        this.checkboxData = checkboxData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_new_category_tile, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        String blogListItem = blogListItemsG.get(position);

        holder.checkbox.setText(blogListItem);

        holder.checkbox.setOnCheckedChangeListener((compoundButton, b) ->{
        if (holder.checkbox.isChecked()) {
            holder.checkbox.setTextColor(contextG.getResources().getColor(R.color.black));
            checkboxData.onCheckboxCheck(blogListItem);
        } else {
            holder.checkbox.setTextColor(contextG.getResources().getColor(R.color.grey));
            checkboxData.onCheckboxUnCheck(blogListItem);
        }
        });
    }

    public void updateData(Set<String> categories) {
        blogListItemsG.clear();
        blogListItemsG = new ArrayList<>(categories);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return blogListItemsG.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final CheckBox checkbox;

        ViewHolder(View itemView) {
            super(itemView);
            checkbox = itemView.findViewById(R.id.material_check_box);
        }
    }
}
