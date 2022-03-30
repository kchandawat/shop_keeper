package com.marquedo.marquedo.Home;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.jjoe64.graphview.GraphView;
import com.marquedo.marquedo.R;

public class sellerSalesStats extends AppCompatActivity {

    GraphView graphView;
    TextView heading, body;
    MaterialButton button;
    int option=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_seller_home);
        heading = (TextView) findViewById(R.id.heading);
        body = (TextView) findViewById(R.id.body);
        button = (MaterialButton) findViewById(R.id.button);

        switch (option){
            case 1 :
                heading.setText("Share your Business on WhatsApp");
                body.setText("Your customers can check your online shop and will be able to place orders.");
                button.setText("Share");
                //Drawable d = getResources().getDrawable(android.R.drawable.ic_wh)
                String uri = "@drawable/ic_whatsapp_share.xml";
                int imageResource = getResources().getIdentifier(uri, null, getPackageName());
                //Drawable icon = getResources().getDrawable(imageResource);
                //button.setIcon(icon);
                break;

            case 2 :
                heading.setText("Upgrade to Marquedo Premium Today");
                body.setText("Get lot more new benefits and easy access by upgrading to Marquedo Premium.");
                button.setText("Upgrade Now");
                break;
        }

        /*graphView = findViewById(R.id.salesGraph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[]{
                new DataPoint(0,1),
                new DataPoint(0,2),
                new DataPoint(1,1),
                new DataPoint(1,2),
                new DataPoint(1,1),
                *//*new DataPoint(2,2),
                new DataPoint(2,1),
                new DataPoint(2,1),
                new DataPoint(0,3),
                new DataPoint(2,3)*//*
        });
        graphView.setTitle("This Months Sales");
        graphView.setTitleColor(R.color.black);
        graphView.setTitleTextSize(18);*/



    }
}