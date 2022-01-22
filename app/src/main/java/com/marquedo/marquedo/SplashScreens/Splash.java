package com.marquedo.marquedo.SplashScreens;

import static com.marquedo.marquedo.OurConstants.OTP_PREFERENCES;
import static com.marquedo.marquedo.OurConstants.SHOP_NAME_KEY;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.google.firebase.auth.FirebaseAuth;
import com.marquedo.marquedo.BusinessDetails.Business_Detail;
import com.marquedo.marquedo.R;
import com.marquedo.marquedo.Home.progress;

public class Splash extends AppCompatActivity {

    private static final int SPLASH_SHOW_TIME = 2000;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen_firstpage);

        firebaseAuth = FirebaseAuth.getInstance();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            getWindow().setStatusBarColor(getResources().getColor(R.color.black_overlay));
        splashIt();
    }

    private void splashIt() {
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            SharedPreferences preferencesCom = getSharedPreferences(OTP_PREFERENCES, Context.MODE_PRIVATE);
            String name = preferencesCom.getString(SHOP_NAME_KEY, null);
            if (firebaseAuth.getCurrentUser() != null) {
                if (name != null)
                    startActivity(new Intent(Splash.this, progress.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                else
                    startActivity(new Intent(Splash.this, Business_Detail.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            } else
                startActivity(new Intent(Splash.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            finish();
        },SPLASH_SHOW_TIME);
    }
}