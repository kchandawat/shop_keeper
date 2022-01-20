package com.marquedo.marquedo.secondary.PnS;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class PnSFragmentAdapter extends FragmentStateAdapter {


    public PnSFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1)
            return new AddServiceFragment();
        return new AddProductFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }

}
