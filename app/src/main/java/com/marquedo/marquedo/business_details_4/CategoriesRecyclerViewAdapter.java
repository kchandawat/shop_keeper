package com.marquedo.marquedo.business_details_4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.marquedo.marquedo.R;

import java.util.List;

public class CategoriesRecyclerViewAdapter extends
        RecyclerView.Adapter<CategoriesRecyclerViewAdapter.ViewHolder> {

    private final List<String> blogListItemsG;
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
                .inflate(R.layout.business_details_4_checkbox_item, parent, false);

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

    @Override
    public int getItemCount() {
        return blogListItemsG.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final CheckBox checkbox;

        ViewHolder(View itemView) {
            super(itemView);
            checkbox = itemView.findViewById(R.id.checkBox);
        }
    }
}
