package com.marquedo.marquedo;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class DeliveryCharges extends AppCompatActivity {

    private EditText ownerName, shopName, storeAddress1, storeAddress2, ownerMobileNumber, city, pincode, state, storeGST;
    private Button saveDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_manage_delivery_charges);
        /*ownerName = findViewById(R.id.ownerName);
        shopName = findViewById(R.id.shopName);
        storeAddress1 = findViewById(R.id.storeAddress1);
        storeAddress2 = findViewById(R.id.storeAddress2);
        ownerMobileNumber = findViewById(R.id.ownerMobileNumber);
        city = findViewById(R.id.storeCity);
        pincode = findViewById(R.id.pin);
        state = findViewById(R.id.storeState);
        storeGST = findViewById(R.id.storeGST);

        ownerName.setInputType(InputType.TYPE_CLASS_TEXT);
        shopName.setInputType(InputType.TYPE_CLASS_TEXT);
        storeAddress1.setInputType(InputType.TYPE_CLASS_TEXT);
        storeAddress2.setInputType(InputType.TYPE_CLASS_TEXT);
        ownerMobileNumber.setInputType(InputType.TYPE_CLASS_NUMBER);
        city.setInputType(InputType.TYPE_CLASS_TEXT);
        state.setInputType(InputType.TYPE_CLASS_TEXT);
        pincode.setInputType(InputType.TYPE_CLASS_NUMBER);
        storeGST.setInputType(InputType.TYPE_CLASS_NUMBER);*/



    }
}