package com.marquedo.marquedo.DesignElements.Manage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.marquedo.marquedo.DesignElements.Manage.MyCustomers.MyCustomerActivity;
import com.marquedo.marquedo.DesignElements.Manage.discounts.DiscountNCouponsActivity;
import com.marquedo.marquedo.DesignElements.OnlinePayment;
import com.marquedo.marquedo.DesignElements.ShopQRPage;
import com.marquedo.marquedo.DesignElements.Manage.MarketingCampaign.MarketingCampaignLanding;
import com.marquedo.marquedo.R;

public class ManageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.designelements_activity_manage);

        CardView myCustomers = findViewById(R.id.manageMyCustomers);
        CardView discounts = findViewById(R.id.discountCoupon);
        CardView marketingCampaign = findViewById(R.id.marketingcampaign);
        CardView deliveryCharges = findViewById(R.id.deliveryCharges);
        CardView extraCharges = findViewById(R.id.extraCharges);
        CardView shopQR = findViewById(R.id.shopQR);
        CardView promotionalDesign = findViewById(R.id.promotionalDesign);
        CardView onlinePayments = findViewById(R.id.onlinePayment);
        CardView reviewsRatings = findViewById(R.id.reviewsRatings);


        reviewsRatings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), ReviewsAndRatingsLanding.class));
            }
        });

        promotionalDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), PromotionalDesignOptions.class));
            }
        });

        onlinePayments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), OnlinePayment.class));
            }
        });

        myCustomers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), MyCustomerActivity.class));
            }
        });

        discounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), DiscountNCouponsActivity.class));
            }
        });

        marketingCampaign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), MarketingCampaignLanding.class));
            }
        });

        deliveryCharges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), DeliveryCharges.class));
            }
        });

        extraCharges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), ExtraCharges.class));
            }
        });

        shopQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), ShopQRPage.class));
            }
        });





    }
}