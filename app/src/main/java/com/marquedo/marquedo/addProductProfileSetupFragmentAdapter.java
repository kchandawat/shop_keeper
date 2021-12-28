package com.marquedo.marquedo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.marquedo.marquedo.ui.Orders_Enquiries.Enquiries.EnquiriesFragment;
import com.marquedo.marquedo.ui.Orders_Enquiries.Orders.OrdersFragment;

public class addProductProfileSetupFragmentAdapter extends FragmentStateAdapter {

    public addProductProfileSetupFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1)
            return new firstThreeAddServiceFragment();
        return new firstThreeAddProductFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }

}
