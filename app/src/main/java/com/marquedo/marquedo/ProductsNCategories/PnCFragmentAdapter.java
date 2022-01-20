package com.marquedo.marquedo.ProductsNCategories;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.marquedo.marquedo.ProductsNCategories.Category.CategoriesFragment;
import com.marquedo.marquedo.ProductsNCategories.Product.ProductsFragment;

public class PnCFragmentAdapter extends FragmentStateAdapter {

    public PnCFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1)
            return new CategoriesFragment();
        return new ProductsFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
