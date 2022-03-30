package com.marquedo.marquedo.DesignElements;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.marquedo.marquedo.R;

public class OnlinePayment extends AppCompatActivity {

    private RadioButton upi;
    private RadioButton account;

    private RelativeLayout upiid;
    private RelativeLayout accountname;
    private RelativeLayout accountnumber;
    private RelativeLayout ifsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.designelements_manage_onlinepayment);

        upi= findViewById(R.id.UPI);
        account = findViewById(R.id.BankAccount);

        upiid = findViewById(R.id.UPILayout);
        accountname = findViewById(R.id.AccountholderLayout);
        accountnumber = findViewById(R.id.AccountnoLayout);
        ifsc = findViewById(R.id.IFSCLayout);


        upi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(upi.isChecked()){
                    upiid.setVisibility(View.VISIBLE);
                    accountname.setVisibility(View.GONE);
                    accountnumber.setVisibility(View.GONE);
                    ifsc.setVisibility(View.GONE);
                }
            }
        });

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(account.isChecked()){
                    upiid.setVisibility(View.GONE);
                    accountname.setVisibility(View.VISIBLE);
                    accountnumber.setVisibility(View.VISIBLE);
                    ifsc.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}