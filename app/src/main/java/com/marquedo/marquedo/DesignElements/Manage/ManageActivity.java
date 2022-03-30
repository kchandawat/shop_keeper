package com.marquedo.marquedo.DesignElements.Manage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.marquedo.marquedo.R;
import com.marquedo.marquedo.DesignElements.Manage.MyCustomers.MyCustomerActivity;
import com.marquedo.marquedo.DesignElements.Manage.discounts.DiscountNCouponsActivity;

public class ManageActivity extends AppCompatActivity
{
    CardView reviews, promotionalDesigns, payment, discountCoupons, deliveryCharge, extraCharge, myCustomers;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.designelements_activity_manage);

        reviews = findViewById(R.id.manage_reviews_ratings);
        promotionalDesigns = findViewById(R.id.manage_promotional_designs);
        payment = findViewById(R.id.manage_online_payment);
        discountCoupons = findViewById(R.id.manage_discount_coupon);
        deliveryCharge = findViewById(R.id.manage_delivery_charges);;
        extraCharge = findViewById(R.id.manage_extra_charges);
        myCustomers = findViewById(R.id.manage_my_customers);;


        /*reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), MyCustomerActivity.class));
            }
        }); */

       /* promotionalDesigns.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), .class));
            }
        });*/


       /* payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), .class));
            }
        }); */


        discountCoupons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), DiscountNCouponsActivity.class));
            }
        });


        deliveryCharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), DeliveryCharges.class));
            }
        });

        extraCharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), ExtraCharges.class));
            }
        });

        myCustomers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), MyCustomerActivity.class));
            }
        });









    }
}