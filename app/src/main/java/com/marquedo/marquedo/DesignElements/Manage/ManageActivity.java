package com.marquedo.marquedo.DesignElements.Manage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.marquedo.marquedo.R;
import com.marquedo.marquedo.DesignElements.Manage.MyCustomers.MyCustomerActivity;
import com.marquedo.marquedo.DesignElements.Manage.discounts.DiscountNCouponsActivity;

public class ManageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.designelements_activity_manage);

        CardView myCustomers = findViewById(R.id.manageMyCustomers);
        CardView discounts = findViewById(R.id.discountCoupon);

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



    }
}