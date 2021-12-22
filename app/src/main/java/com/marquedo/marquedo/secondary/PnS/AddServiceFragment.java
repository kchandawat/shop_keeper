package com.marquedo.marquedo.secondary.PnS;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.marquedo.marquedo.R;
import com.marquedo.marquedo.progress;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddServiceFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddServiceFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddServiceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddServiceFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddServiceFragment newInstance(String param1, String param2) {
        AddServiceFragment fragment = new AddServiceFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_service, container, false);

        view.findViewById(R.id.add_service_btn).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                View view2=getLayoutInflater().inflate(R.layout.fragment_serviced_added_success,null);
                BottomSheetDialog bottomSheetDialog1= new BottomSheetDialog(v.getContext());
                bottomSheetDialog1.setContentView(view2);
                bottomSheetDialog1.show();

                bottomSheetDialog1.findViewById(R.id.submit_button).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        bottomSheetDialog1.dismiss();
                        startActivity(new Intent(v.getContext(), progress.class)
                                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                    }
                });
            }
        });

        return view;
    }
}