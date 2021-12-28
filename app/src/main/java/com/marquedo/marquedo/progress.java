package com.marquedo.marquedo;

import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.marquedo.marquedo.databinding.ActivityProgressBinding;

public class progress extends AppCompatActivity {

    private ActivityProgressBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        binding = ActivityProgressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_orders_enquiries,
                R.id.navigation_products_categories, R.id.navigation_dashboard,
                //R.id.navigation_notifications,
                R.id.navigation_services)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_progress);
        binding.navView.setItemIconTintList(null);
        NavigationUI.setupWithNavController(binding.navView, navController);
//        NavigationUI.setupWithNavController(binding.navView, Navigation.findNavController(this,R.id.));
    }

//    @Override
//    public void onBackPressed(){
//        FragmentManager fm = getFragmentManager();
//        if (fm.getBackStackEntryCount() > 0) {
//            Log.i("MainActivity", "popping backstack");
//            fm.popBackStack();
//        } else {
//            Log.i("MainActivity", "nothing on backstack, calling super");
//            super.onBackPressed();
//        }
//    }

}