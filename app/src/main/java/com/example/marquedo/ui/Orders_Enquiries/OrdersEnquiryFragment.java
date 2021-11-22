package com.example.marquedo.ui.Orders_Enquiries;

import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.marquedo.MyFragmentAdapter;
import com.example.marquedo.R;
import com.google.android.material.tabs.TabLayout;

public class OrdersEnquiryFragment extends Fragment {

    private OrdersEnquiryViewModel mViewModel;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private MyFragmentAdapter adapter;

    public static OrdersEnquiryFragment newInstance() {
        return new OrdersEnquiryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_order_enquiry_page, container, false);

        TextView viewdetails = v.findViewById(R.id.tvViewDetails);

        tabLayout = v.findViewById(R.id.order_enquiry_tab);
        viewPager2 = v.findViewById(R.id.view_pager_order_page);

        tabLayout.addTab(tabLayout.newTab().setText("Orders"));
        tabLayout.addTab(tabLayout.newTab().setText("Enquiries"));

        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        adapter = new MyFragmentAdapter(fragmentManager, getLifecycle());
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



        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(OrdersEnquiryViewModel.class);
        // TODO: Use the ViewModel
    }

}