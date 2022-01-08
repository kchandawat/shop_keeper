package com.marquedo.marquedo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.io.ByteArrayOutputStream;

public class Business_card_templates extends AppCompatActivity {

    private Button preview;
    private LinearLayout card1;
    private LinearLayout card2;
    private LinearLayout card3;
    private LinearLayout card4;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_manage_promotionaldesign_business_card_templates);

        card1 = (LinearLayout) findViewById(R.id.biz_card1);
        card2 = (LinearLayout) findViewById(R.id.biz_card2);
        card3 = (LinearLayout) findViewById(R.id.biz_card3);
        card4 = (LinearLayout) findViewById(R.id.biz_card4);

        preview = (Button) findViewById(R.id.previewBusinessCard);
        //Intent preview = new Intent(getConte)
        Intent previewCard = new Intent(this, Business_card_preview.class);

        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.marquedo_biz_card_1);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                previewCard.putExtra("template", byteArray);
            }
        });

        card2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.marquedo_biz_card_4);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                previewCard.putExtra("template", byteArray);
            }
        });

        card3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.marquedo_biz_card_3);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                previewCard.putExtra("template", byteArray);
            }
        });

        card4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.marquedo_biz_card_2);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                previewCard.putExtra("template", byteArray);
            }
        });

        preview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(previewCard);
            }
        });
    }


}