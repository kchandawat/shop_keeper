package com.marquedo.marquedo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import androidx.fragment.app.Fragment;
import android.app.Fragment;
public class Step4Fragment extends Fragment {

    View view;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
// Inflate the layout for this fragment
        view = inflater.inflate(R.layout.marketing_campaign_step4_fragment, container, false);

// get the reference of Button

        return view;
    }

}
