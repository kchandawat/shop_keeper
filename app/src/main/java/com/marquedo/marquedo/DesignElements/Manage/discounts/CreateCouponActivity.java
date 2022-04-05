package com.marquedo.marquedo.DesignElements.Manage.discounts;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.marquedo.marquedo.R;

public class CreateCouponActivity extends AppCompatActivity {

    EditText discountPercent, discountAmount;
    RadioButton percent, flatDiscount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.designelements_manage_discounts_activity_create_coupon);

        findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        percent = (RadioButton) findViewById(R.id.percent);
        flatDiscount = (RadioButton) findViewById(R.id.flatDis);
        discountPercent = findViewById(R.id.discountPercent);
        discountAmount = findViewById(R.id.discountAmount);

        percent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                discountPercent.setEnabled(true);
                discountPercent.setBackgroundResource(R.drawable.edit_text_layout);
                discountPercent.setHintTextColor(getResources().getColor(R.color.almost_black));
                discountAmount.setEnabled(false);
                discountAmount.getText().clear();
                discountAmount.setBackgroundResource(R.color.light_grey);
                discountAmount.setHintTextColor(getResources().getColor(R.color.material_grey_500));

            }
        });

        flatDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                discountPercent.setEnabled(false);
                discountPercent.getText().clear();
                discountPercent.setBackgroundResource(R.color.light_grey);
                discountPercent.setHintTextColor(getResources().getColor(R.color.material_grey_500));
                discountAmount.setEnabled(true);
                discountAmount.setBackgroundResource(R.drawable.edit_text_layout);
                discountAmount.setHintTextColor(getResources().getColor(R.color.almost_black));
            }
        });


    }
}