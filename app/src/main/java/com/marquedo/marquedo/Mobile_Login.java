package com.marquedo.marquedo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class Mobile_Login extends AppCompatActivity {

    private TextInputLayout textInputEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_login);
        MaterialButton sendOtp = findViewById(R.id.continue_button);
        ImageButton backButton = findViewById(R.id.login_back_button);
        textInputEditText = findViewById(R.id.textInputEditText);
        sendOtp.setOnClickListener(view -> StartOTP());
        backButton.setOnClickListener(view -> BackButtonPress());
    }

    private void BackButtonPress() {
        super.onBackPressed();
    }

    private void StartOTP() {
        Intent OTP= new Intent(this,otp.class);
        String phoneNumber = Objects.requireNonNull(textInputEditText.getEditText()).getText().toString();
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Please check!")
                .setMessage("By tapping Okay you agree to the terms & conditions. Standard SMS charges may apply")
                .setPositiveButton("Okay", (dialogInterface, i) -> {
                    OTP.putExtra(OurConstants.PHONE_NUMBER_LOGIN, phoneNumber);
                    startActivity(OTP);
                })
                .create()
                .show();
    }
}