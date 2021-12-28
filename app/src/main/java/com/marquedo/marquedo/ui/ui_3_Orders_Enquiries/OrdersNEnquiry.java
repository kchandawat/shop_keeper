package com.marquedo.marquedo.ui.ui_3_Orders_Enquiries;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.marquedo.marquedo.R;
import com.google.android.material.tabs.TabLayout;

public class OrdersNEnquiry extends Fragment {

    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private HorizontalScrollView filters;
    private OrdersEnquiriesFragmentAdapter adapter;

    public static OrdersNEnquiry newInstance() {
        return new OrdersNEnquiry();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ui_ui_3_order_enquiry_page, container, false);

        tabLayout = view.findViewById(R.id.order_enquiry_tab);
        viewPager2 = view.findViewById(R.id.view_pager_order_page);
        filters = view.findViewById(R.id.Orders_Enquiries_filter);

        tabLayout.addTab(tabLayout.newTab().setText("Orders"));
        tabLayout.addTab(tabLayout.newTab().setText("Enquiries"));

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        adapter = new OrdersEnquiriesFragmentAdapter(fragmentManager, getLifecycle());
        viewPager2.setAdapter(adapter);

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

        return view;
    }

}
