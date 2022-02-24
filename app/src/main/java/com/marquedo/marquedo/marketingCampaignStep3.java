package com.marquedo.marquedo;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class marketingCampaignStep3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.marketing_campaign_step3);
        initCustomerCategory();
    }
    private void initCustomerCategory(){
        final AutoCompleteTextView customerContact = findViewById(R.id.contact_category);
        ArrayList<String> categoryList = getContactCategory();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(marketingCampaignStep3.this, android.R.layout.simple_spinner_item,categoryList);
        customerContact.setAdapter(adapter);
    }
    private ArrayList<String> getContactCategory(){
        ArrayList<String> categories = new ArrayList<>();
        categories.add("All Customers");
        categories.add("Subscribed");
        categories.add("Past Customers");
        categories.add("Individuals");
        return categories;

    }
}