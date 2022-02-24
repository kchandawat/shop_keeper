package com.marquedo.marquedo;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.marquedo.marquedo.databinding.MarketingCampaignStepBinding;

//import androidx.fragment.app.Fragment;
//import android.app.Fragment;

public class marketingCampaignStep extends AppCompatActivity {

    private EditText campaignName;
    MaterialButton next;
    ImageButton back;
    MaterialButton prev;
    MarketingCampaignStepBinding binding;
    String[] descriptionData = {"Step 1","Step 2","Step 3","Step 4"};
    int currentState = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MarketingCampaignStepBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.steps.setLabels(descriptionData)
                .setBarColorIndicator(getResources().getColor(R.color.grey))
                .setProgressColorIndicator(getResources().getColor(R.color.orange))
                .setLabelColorIndicator(getResources().getColor(R.color.black))
                .setCompletedPosition(0)
                .drawView();
        binding.steps.setCompletedPosition(currentState);

        loadFragment(new Step1Fragement());
        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentState<=(descriptionData.length-1)){
                    if((currentState==0))
                        loadFragment(new Step2Fragement());
                    if(currentState==1)
                        loadFragment(new Step3Fragment());
                    if(currentState==2)
                        loadFragment(new Step4Fragment());
                    if(currentState==3) {
                        loadFragment(new Step1Fragement());
                    }
                    if(currentState==3) {
                        binding.next.setText("Pay & Launch Campaign");
                        //binding.next.setVisibility(View.INVISIBLE);
                    }
                    //onBackPressed();
                    currentState = currentState+1;
                    binding.steps.setCompletedPosition(currentState).drawView();
                    Log.d("currentState = ", currentState+"");

                }
            }
        });
        back = (ImageButton) findViewById(R.id.imageButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentState>=0) {
                    if ((currentState == 0)){
                        /*Intent intent = getIntent();
                        finish();
                        startActivity(intent);*/
                        recreate();
                    }
                    if (currentState == 1)
                        loadFragment(new Step1Fragement());
                    if (currentState == 2)
                        loadFragment(new Step2Fragement());
                    if (currentState == 3) {
                        loadFragment(new Step3Fragment());
                    }
                    if (currentState == 3) {
                        binding.next.setText("Pay & Launch Campaign");
                        //binding.next.setVisibility(View.INVISIBLE);
                    }
                    //onBackPressed();
                    currentState = currentState - 1;
                    binding.steps.setCompletedPosition(currentState).drawView();
                    Log.d("currentState = ", currentState + "");
                }
            }
        });

    }

    /*@Override
    public void onBackPressed() {
        //super.onBackPressed();
        currentState--;
        if(currentState>=0){
            if((currentState==0))
                loadFragment(new Step2Fragement());
            if(currentState==1)
                loadFragment(new Step3Fragment());
            if(currentState==2)
                loadFragment(new Step4Fragment());
            if(currentState==3) {
                loadFragment(new Step1Fragement());
            }
            if(currentState==3) {
                binding.next.setText("Pay & Launch Campaign");
                //binding.next.setVisibility(View.INVISIBLE);
            }
            //onBackPressed();
            //currentState = currentState+1;
            binding.steps.setCompletedPosition(currentState).drawView();
            Log.d("currentState = ", currentState+"");

        }
    }
   */

    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit(); // save the changes
    }


}