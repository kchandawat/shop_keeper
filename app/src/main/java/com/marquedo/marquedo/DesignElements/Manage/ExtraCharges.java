package com.marquedo.marquedo.DesignElements.Manage;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.marquedo.marquedo.R;

public class ExtraCharges extends AppCompatActivity {

    private CheckBox deliveryCheckbox ;
    private RelativeLayout deliverOnlinePay;
    private EditText deliveryDetails;
    //private Switch gst;
    private RelativeLayout gstNumber;
    private RelativeLayout gstPercent;
    private EditText gstnum;
    private EditText gstpercentage;
    private SwitchCompat gst;
    private EditText delcharges;
    private EditText freedelcharges;
    private ImageButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.designelements_manage_extra_charges);
        deliveryCheckbox = (CheckBox) findViewById(R.id.deliveryCheckbox);
        deliverOnlinePay= (RelativeLayout) findViewById(R.id.deliveryOnlinePay);
        deliveryDetails = (EditText) findViewById(R.id.deliverydetails);
        gst = (SwitchCompat) findViewById(R.id.gstswitch);
        gstNumber = (RelativeLayout) findViewById(R.id.GSTLayout1);
        gstPercent = (RelativeLayout) findViewById(R.id.GSTLayout2);
        delcharges = (EditText) findViewById(R.id.delcharges);
        freedelcharges = (EditText) findViewById(R.id.freedelcharges);
        gstnum = (EditText) findViewById(R.id.GST1);
        gstpercentage = (EditText) findViewById(R.id.GST2);

        delcharges.setInputType(InputType.TYPE_CLASS_NUMBER);
        freedelcharges.setInputType(InputType.TYPE_CLASS_NUMBER);
        gstnum.setInputType(InputType.TYPE_CLASS_NUMBER);
        gstpercentage.setInputType(InputType.TYPE_CLASS_NUMBER);

        back = findViewById(R.id.imageButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        deliveryCheckbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(deliveryCheckbox.isChecked()){
                    deliverOnlinePay.setVisibility(View.VISIBLE);
                    deliveryDetails.setEnabled(true);
                    //deliveryDetails.setFocusable(true);
                    deliveryDetails.setInputType(InputType.TYPE_CLASS_NUMBER);
                }
                else{
                    deliverOnlinePay.setVisibility(View.GONE);
                    deliveryDetails.setEnabled(false);
                    //deliveryDetails.setFocusable(false);
                    //deliveryDetails.setInputType(InputType.TYPE_CLASS_NUMBER);
                }
            }
        });
        gst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(gst.isChecked()){
                    gstNumber.setVisibility(View.VISIBLE);
                    gstNumber.setEnabled(true);
                    gstPercent.setVisibility(View.VISIBLE);
                    gstPercent.setEnabled(true);
                }
                else{
                    gstNumber.setVisibility(View.GONE);
                    gstNumber.setEnabled(false);
                    gstPercent.setVisibility(View.GONE);
                    gstPercent.setEnabled(false);
                }
            }
        });



        /*if(deliveryCheckbox.isChecked()){
            //deliverOnlinePay.setVisibility(View.VISIBLE);
            deliveryDetails.setEnabled(true);
        }*/

    }

}