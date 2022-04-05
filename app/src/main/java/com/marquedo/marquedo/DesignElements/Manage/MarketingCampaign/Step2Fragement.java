package com.marquedo.marquedo.DesignElements.Manage.MarketingCampaign;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import androidx.fragment.app.Fragment;
import android.app.Fragment;

import com.marquedo.marquedo.R;

public class Step2Fragement extends Fragment {

    View view;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
// Inflate the layout for this fragment
        view = inflater.inflate(R.layout.designelements_manage_marketing_campaign_step2_fragment, container, false);
// get the reference of Button

        return view;
    }

}
