package com.marquedo.marquedo.DesignElements.Manage.discounts;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.marquedo.marquedo.R;

import java.util.ArrayList;

public class CouponListAdapter extends RecyclerView.Adapter<CouponListAdapter.ViewHolder> {

    ArrayList<CouponDataModel> data;

    public CouponListAdapter(ArrayList<CouponDataModel> data) {
        this.data = data;
    }


    @NonNull
    @Override
    public CouponListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CouponListAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.designelements_manage_discounts_coupons_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull CouponListAdapter.ViewHolder holder, int position) {
        holder.couponCode.setText(data.get(position).couponCode);
        holder.couponStatus.setChecked(data.get(position).couponStatus);
        holder.usageCount.setText(String.valueOf(data.get(position).usageCount));
        holder.salesCount.setText(String.valueOf(data.get(position).salesCount));
        holder.couponOffer.setText(data.get(position).couponOffer);

        holder.shareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, holder.couponCode.getText());
                // (Optional) Here we're setting the title of the content
                sendIntent.putExtra(Intent.EXTRA_TITLE, "Share Your Coupon!");

                // (Optional) content URI to an image to be displayed
                //sendIntent.setData(contentUri);
                sendIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                // Show the Sharesheet
                view.getContext().startActivity(Intent.createChooser(sendIntent, null));
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView couponCode;
        SwitchCompat couponStatus;
        TextView usageCount;
        TextView salesCount;
        TextView couponOffer;
        RelativeLayout shareBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            couponCode = itemView.findViewById(R.id.coupon_code);
            couponStatus = itemView.findViewById(R.id.coupon_state);
            usageCount = itemView.findViewById(R.id.used_count);
            salesCount = itemView.findViewById(R.id.sales_count);
            couponOffer = itemView.findViewById(R.id.coupon_offer);
            shareBtn = itemView.findViewById(R.id.share_coupon);
        }
    }
}

