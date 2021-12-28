package com.marquedo.marquedo.ui.ui_1_home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.marquedo.marquedo.progress_6.AddNewProductNC;
import com.marquedo.marquedo.R;
import com.marquedo.marquedo.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        Button add=(Button) root.findViewById(R.id.continue_button);
        Intent addProduct=new Intent(getContext(), AddNewProductNC.class);

add.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(addProduct);
    }
});


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}