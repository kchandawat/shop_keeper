package com.marquedo.marquedo.Home;

import static android.content.ContentValues.TAG;
import static com.marquedo.marquedo.OurConstants.BUSINESS_UNIQUE_NAME;
import static com.marquedo.marquedo.OurConstants.OTP_PREFERENCES;
import static com.marquedo.marquedo.OurConstants.SHOP_NAME_KEY;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.marquedo.marquedo.BusinessDetails.Business_Detail;
import com.marquedo.marquedo.R;
import com.marquedo.marquedo.databinding.HomeFragmentHomeBinding;

import org.w3c.dom.Text;

import java.util.HashSet;

import moe.feng.common.stepperview.VerticalStepperItemView;
import moe.feng.common.stepperview.VerticalStepperView;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private HomeFragmentHomeBinding binding;
    private LinearProgressIndicator progressIndicator;
    private Button add;
    private TextView tvProgress;
    private String businessUniqueName;
    private String businessName;
    private FirebaseFirestore db;
    private SharedPreferences sharedPreferences;
    private VerticalStepperItemView step0;
    private VerticalStepperItemView step1;
    private VerticalStepperItemView step2;
    private int totalItmes = 0;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = HomeFragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        initializations(root);


        businessUniqueName = sharedPreferences.getString(BUSINESS_UNIQUE_NAME, "").toString();
        businessName = sharedPreferences.getString(SHOP_NAME_KEY, "").toString();
        Log.d("Business unique name", businessUniqueName);

        ProgressDialog dialog = ProgressDialog.show(getContext(), "",
                "Loading Please wait...", true);

        DocumentReference storeRef = db.collection("Store").document(businessUniqueName);

                storeRef.collection("products").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            totalItmes += task.getResult().size();
                            Log.d("tast2",String.valueOf(task.getResult().size())+ "" );
                            Log.d("doc size", totalItmes + "");

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

        if(totalItmes < 3) {
            storeRef.collection("services").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task1) {
                    if (task1.isSuccessful()) {
                        totalItmes += task1.getResult().size();
                        Log.d("tast1",String.valueOf(task1.getResult().size())+ "" );
                        Log.d("doc size", totalItmes + "");


                    }
                }
            });
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                {
                    if(totalItmes <= 3 ){
                        int percentage = 30 + (totalItmes * 60/3);
//                                step2.setActivated(true);
//                                getContext().obtainStyledAttributes()

                        progressIndicator.setProgress(percentage);
                        tvProgress.setText(String.valueOf(percentage).toString() + "%");
                    }
                    else if(totalItmes > 3){
                        progressIndicator.setProgress(90);
                        tvProgress.setText("90%");
                    }
                    if(totalItmes == 3)
                    {
                        step1.setLineColor(getResources().getColor(R.color.yellow));
                        step2.setActivatedColor(getResources().getColor(R.color.yellow));
                        TextView text1 = root.findViewById(R.id.step3text1);
                        TextView text2 = root.findViewById(R.id.step3text2);
                        text1.setTextColor(getResources().getColor(R.color.black));
                        text2.setTextColor(getResources().getColor(R.color.black));
                        step2.setActivated(true);
                    }

                    dialog.dismiss();

                }
            }
        }, 4000);// 2000 milliseconds = 2seconds


        Intent add_product = new Intent(getContext(), addProductAndServicesProfilesetupActivity.class);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(add_product);
            }
        });


        return root;
    }

    public void initializations(View root){
        db = FirebaseFirestore.getInstance();
        tvProgress = root.findViewById(R.id.tvProgress);
        sharedPreferences = requireContext().getSharedPreferences(OTP_PREFERENCES, Context.MODE_PRIVATE);
        progressIndicator = root.findViewById(R.id.linearProgressBar);
        add = (Button) root.findViewById(R.id.continue_button);
        step0 = root.findViewById(R.id.homestepper_0);
        step1 = root.findViewById(R.id.homestepper_1);
        step2 = root.findViewById(R.id.homestepper_2);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}