package com.example.marquedo.ui.Prod_n_Cat;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.marquedo.ui.Prod_n_Cat.Category.CategoriesFragment;
import com.example.marquedo.ui.Prod_n_Cat.Product.ProductsFragment;

public class PnCFragmentAdapter extends FragmentStateAdapter {

    public PnCFragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1)
            return new ProductsFragment();
        return new CategoriesFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
