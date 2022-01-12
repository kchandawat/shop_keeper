package com.marquedo.marquedo.ui.Prod_n_Cat;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.HorizontalScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.marquedo.marquedo.R;
import com.marquedo.marquedo.addProductAndServicesProfilesetupActivity;
import com.marquedo.marquedo.secondary.PnS.AddProductFragment;
import com.google.android.material.tabs.TabLayout;
import com.marquedo.marquedo.secondary.PnS.AddProductNServiceActivity;

public class product_n_category extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private HorizontalScrollView filters;
    private PnCFragmentAdapter adapter;
    private Button AddProduct;

    public static product_n_category newInstance() {
        return new product_n_category();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products_n_category, container, false);

        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager2 = view.findViewById(R.id.viewPager);
        filters = view.findViewById(R.id.filter_view);
        AddProduct = view.findViewById(R.id.add_new_prod_button);

        AddProduct.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), addProductAndServicesProfilesetupActivity.class);
                startActivity(intent);

            }
        });




        tabLayout.addTab(tabLayout.newTab().setText("Products"));
        tabLayout.addTab(tabLayout.newTab().setText("Categories"));

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        adapter = new PnCFragmentAdapter(fragmentManager, getLifecycle());
        viewPager2.setAdapter(adapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
                if ((tab.getPosition() == 1))
                    filters.setVisibility(View.INVISIBLE);
                else
                    filters.setVisibility(View.VISIBLE);

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

        return view;
    }

}
