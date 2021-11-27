package com.marquedo.marquedo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;

public class sellerSalesStats extends AppCompatActivity {

    GraphView graphView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seller_home);
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