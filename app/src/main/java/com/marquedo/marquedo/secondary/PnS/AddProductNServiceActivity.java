package com.marquedo.marquedo.secondary.PnS;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.marquedo.marquedo.R;

public class AddProductNServiceActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private PnSFragmentAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_addproductsandservices);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.viewPager);

        findViewById(R.id.back_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddProductNServiceActivity.super.onBackPressed();
            }
        });

        tabLayout.addTab(tabLayout.newTab().setText("Product"));
        tabLayout.addTab(tabLayout.newTab().setText("Service"));

        FragmentManager fragmentManager = getSupportFragmentManager();
        adapter = new PnSFragmentAdapter(fragmentManager, getLifecycle());
        viewPager2.setAdapter(adapter);

        String intentFragment = getIntent().getExtras().getString("frgToLoad");
        if(intentFragment != null){
            if(intentFragment.equalsIgnoreCase("service")){
                viewPager2.setCurrentItem(1);
                tabLayout.setScrollPosition(1,0f,true);
            }
            else {
                viewPager2.setCurrentItem(0);
                tabLayout.setScrollPosition(0,0f,true);
            }
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });
    }

}