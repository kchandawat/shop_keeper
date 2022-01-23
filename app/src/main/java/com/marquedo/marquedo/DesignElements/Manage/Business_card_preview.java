package com.marquedo.marquedo.DesignElements.Manage;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.marquedo.marquedo.R;

public class Business_card_preview extends AppCompatActivity {

    private TextView shopName;
    private TextView shopWebsite;
    private TextView shopPhone;

    private Button backtotemplates;
    private LinearLayout bizCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.designelements_manage_promotionaldesign_business_card_preview);
        String shopname = "Super Shopping Centre";
        String shopwebsite = "marquedo.io/supershoppingcentre";
        String shopphone = "+91 1234567890";

        Intent templates = new Intent(this, Business_card_templates.class);
        backtotemplates = (Button) findViewById(R.id.backToTemplates);

        Bundle extras = getIntent().getExtras();
        byte[] byteArray = extras.getByteArray("template");
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        bizCard = (LinearLayout) findViewById(R.id.biz_card);
        Drawable background = new BitmapDrawable(getResources(), bmp);
        bizCard.setBackground(background);
        //Bitmap bmp = (Bitmap) getIntent().getParcelableArrayExtra("template2");


        shopName = findViewById(R.id.shopName);
        shopWebsite = findViewById(R.id.shopWebsite);
        shopPhone = findViewById(R.id.shopPhone);

        shopName.setText(shopname);
        shopPhone.setText(shopphone);
        shopWebsite.setText(shopwebsite);

        backtotemplates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(templates);
            }
        });

    }
}