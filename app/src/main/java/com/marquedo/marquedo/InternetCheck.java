package com.marquedo.marquedo;

import static com.marquedo.marquedo.OurConstants.TYPE_MOBILE;
import static com.marquedo.marquedo.OurConstants.TYPE_NOT_CONNECTED;
import static com.marquedo.marquedo.OurConstants.TYPE_WIFI;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class InternetCheck {

    public boolean internetConnected;
    public BroadcastReceiver broadcastReceiver;
    public Context context;
    public Snack snack;

    public InternetCheck(boolean internetConnected, BroadcastReceiver broadcastReceiver, Context context) {
        this.internetConnected = internetConnected;
        this.broadcastReceiver = broadcastReceiver;
        this.context = context;
        snack = new Snack(context);
    }

    public static int getConnectionType(Context context) {
        int result = 0; // Returns connection type. 0: none; 1: mobile data; 2: wifi
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (cm != null) {
                NetworkCapabilities capabilities = cm.getNetworkCapabilities(cm.getActiveNetwork());
                if (capabilities != null) {
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                        result = 2;
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                        result = 1;
                    }
                }
            }
        } else {
            if (cm != null) {
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                if (activeNetwork != null) {
                    // connected to the internet
                    if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                        result = 2;
                    } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                        result = 1;
                    }
                }
            }
        }
        return result;
    }

    public static String getConnectivityStatusString(Context context) {
        int conn = getConnectionType(context);
        String status = null;
        if (conn == TYPE_WIFI) {
            status = "Wifi enabled";
        } else if (conn == TYPE_MOBILE) {
            status = "Mobile data enabled";
        } else if (conn == TYPE_NOT_CONNECTED) {
            status = "Not connected to Internet";
        }
        return status;
    }

    public void registerInternetCheckReceiver() {
        IntentFilter internetFilter = new IntentFilter();
        internetFilter.addAction("android.net.wifi.STATE_CHANGE");
        internetFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        context.registerReceiver(broadcastReceiver, internetFilter);
    }

    public void setSnackbarMessage(String status, View view) {
        String internetStatus;
        if (status.equalsIgnoreCase("Wifi enabled")) {
            internetStatus = "Wifi Connected";
        } else if (status.equalsIgnoreCase("Mobile data enabled")) {
            internetStatus = "Mobile Data Connected";
        } else {
            internetStatus = "Lost Internet Connection";
        }
        if (internetStatus.equalsIgnoreCase("Lost Internet Connection")) {
            if (internetConnected) {
                snack.snackBar(view, internetStatus);
                internetConnected = false;
            }
        } else {
            if (!internetConnected) {
                internetConnected = true;
                snack.snackBar(view, internetStatus);
            }
        }
    }

    public void snackBar(View view, String snackBarText) {
        Snackbar.make(view, snackBarText, Snackbar.LENGTH_LONG)
                .setBackgroundTint(context.getResources().getColor(R.color.yellow))
                .setTextColor(context.getResources().getColor(R.color.sienna_brown))
                .show();
    }
}
