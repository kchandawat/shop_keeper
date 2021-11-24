package com.example.marquedo.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.marquedo.R;
import com.example.marquedo.databinding.FragmentDashboardBinding;
import com.example.marquedo.logoutAction;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView wallet = (TextView) root.findViewById(R.id.myWallet);
        TextView subscription = (TextView) root.findViewById(R.id.subscriptionPlans);
        TextView shareApp = (TextView) root.findViewById(R.id.shareMarquedoApp);
        TextView howToUseApp = (TextView) root.findViewById(R.id.howToUseApp);
        TextView privacy = (TextView) root.findViewById(R.id.privacyPolicy);
        TextView logout = (TextView) root.findViewById(R.id.logout);

        Intent subscriptionPlans = new Intent(getContext(), SubscriptionPlans.class);
        Intent logout_action = new Intent(getContext(), logoutAction.class);

        /*wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity();
            }
        });*/
        subscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(subscriptionPlans);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(logout_action);
            }
        });

        Button edit = root.findViewById(R.id.editButton);


        /*final TextView textView = binding.shopName;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}