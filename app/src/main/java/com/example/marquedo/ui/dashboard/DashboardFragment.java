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
import com.google.android.material.bottomsheet.BottomSheetDialog;
//import com.example.marquedo.logoutAction;

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
        Intent privacyPolicy = new Intent(getContext(), privacypolicy.class);
        Intent myWallet = new Intent(getContext(), Mywallet.class);
        //Intent logout_action = new Intent(getContext(), logoutAction.class);
        /*wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity();
            }
        });*/
        wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(myWallet);
            }
        });

        subscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(subscriptionPlans);
            }
        });

        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(privacyPolicy);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view2 = getLayoutInflater().inflate(R.layout.logout_action,null);
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(view.getContext());
                bottomSheetDialog.setContentView(view2);
                bottomSheetDialog.show();
                Button yes = (Button) view2.findViewById(R.id.Yeslogout_button);
                Button no = (Button) view2.findViewById(R.id.Nologout_button);
                Button close = (Button) view2.findViewById(R.id.close_sheet);
                /*Intent noLogout = new Intent(getContext(), DashboardViewModel.class);

                no.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(noLogout);
                    }
                });
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(noLogout);
                    }
                });*/
                yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });
            }
        });
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(logout_action);
//            }
//        });

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