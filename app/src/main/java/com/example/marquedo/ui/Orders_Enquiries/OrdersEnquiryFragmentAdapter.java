package com.example.marquedo.ui.Orders_Enquiries;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.marquedo.ui.Orders_Enquiries.Enquiries.EnquiriesFragment;
import com.example.marquedo.ui.Orders_Enquiries.Orders.OrdersFragment;
import com.example.marquedo.ui.Prod_n_Cat.Product.ProductsFragment;

class OrdersEnquiriesFragmentAdapter extends FragmentStateAdapter {

    public OrdersEnquiriesFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1)
            return new EnquiriesFragment();
        return new OrdersFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
