package com.marquedo.marquedo.DesignElements.Manage.discounts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.marquedo.marquedo.R;

import java.util.ArrayList;

public class DiscountNCouponsActivity extends AppCompatActivity {

    ArrayList<CouponDataModel> data = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.designelements_manage_discounts_activity_discount_ncoupons);

        data.add(new CouponDataModel("FEST50", "Flat 50rs OFF"));
        data.add(new CouponDataModel("FEST150", "Flat 150rs OFF"));
        data.add(new CouponDataModel("FEST50", "Flat 50rs OFF"));
        data.add(new CouponDataModel("FEST150", "Flat 150rs OFF"));
        data.add(new CouponDataModel("FEST50", "Flat 50rs OFF"));
        data.add(new CouponDataModel("FEST150", "Flat 150rs OFF"));
        data.add(new CouponDataModel("FEST50", "Flat 50rs OFF"));
        data.add(new CouponDataModel("FEST150", "Flat 150rs OFF"));


        MaterialButton createNewCoupon = findViewById(R.id.create_new_coupon);
        createNewCoupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), CreateCouponActivity.class));
            }
        });

        findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.discounts_list);
        recyclerView.setAdapter(new CouponListAdapter(data));
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));






    }
}