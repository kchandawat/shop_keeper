package com.marquedo.marquedo;

import android.content.Context;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class Snack {

    public Context context;

    public Snack(Context context)
    {
        this.context = context;
    }

    public void snackBar(View view, String snackBarText)
    {
        Snackbar.make(view, snackBarText, Snackbar.LENGTH_LONG)
                .setBackgroundTint(context.getResources().getColor(R.color.yellow))
                .setTextColor(context.getResources().getColor(R.color.sienna_brown))
                .show();
    }


}





















