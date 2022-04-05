package com.marquedo.marquedo.DesignElements.Manage.MarketingCampaign;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.marquedo.marquedo.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MarketingCampaignLanding extends AppCompatActivity {

    private TextView ordersFromCampaigns, salesFromCampaigns,marketingCost, smsMessagesSent;
    private TextView marketingDateTime;
    private TextView storeViews, ordersCount, salesCount, costOfCampaign;
    private MaterialButton createNewCampaign;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView((R.layout.designelements_manage_marketing_campaign_landing));

        back = findViewById(R.id.imageButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        String marketingDteTime;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat;
        simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm aaa");
        marketingDteTime = simpleDateFormat.format(calendar.getTime()).toString();

        ordersFromCampaigns = (TextView) this.findViewById(R.id.ordersfromcampaigns);
        salesFromCampaigns = (TextView) this.findViewById(R.id.salesfromcampaigns);
        marketingCost = (TextView) this.findViewById(R.id.marketingcost);
        smsMessagesSent = (TextView) this.findViewById(R.id.smssent);
        marketingDateTime = (TextView) this.findViewById(R.id.marketingdatetime);
        storeViews = (TextView) this.findViewById(R.id.storeviewscount);
        ordersCount = (TextView) this.findViewById(R.id.orderscount);
        salesCount = (TextView) this.findViewById(R.id.sales);
        costOfCampaign = (TextView) this.findViewById(R.id.costofcampaign);

        createNewCampaign = (MaterialButton) this.findViewById(R.id.createnewcampaign);

        ordersFromCampaigns.setText(Integer.toString(2));
        salesFromCampaigns.setText(Integer.toString(0));
        marketingCost.setText(Double.toString(2.50));
        smsMessagesSent.setText(Integer.toString(5));
        marketingDateTime.setText(marketingDteTime);
        storeViews.setText(Integer.toString(7));
        ordersCount.setText(Integer.toString(10));
        salesCount.setText(Double.toString(0.00));
        costOfCampaign.setText(Double.toString(0.75));



        createNewCampaign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MarketingCampaignStep.class);
                startActivity(intent);
            }
        });



    }
}