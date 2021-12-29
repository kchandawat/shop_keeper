package com.marquedo.marquedo.ui.dashboard;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.marquedo.marquedo.R;
import com.marquedo.marquedo.ui.dashboard.discounts.DisLandingFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ManageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ManageFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ManageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DashLandingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ManageFragment newInstance(String param1, String param2) {
        ManageFragment fragment = new ManageFragment();
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
        View view = inflater.inflate(R.layout.fragment_manage, container, false);
        CardView myCustomers = view.findViewById(R.id.manageMyCustomers);
        CardView discountCoupons = view.findViewById(R.id.discountCoupon);
        ImageButton backBtn = view.findViewById(R.id.btnBackArrowManage);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                getActivity().onBackPressed();
//                getFragmentManager().popBackStackImmediate();
//                getFragmentManager().popBackStack();
//                getActivity().getSupportFragmentManager().popBackStack();

                Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag("ManageFragment");
                getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();


            }
        });

        myCustomers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyCustomerFragment nextFrag= new MyCustomerFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(((ViewGroup)getView().getParent()).getId(), nextFrag, "MyCustomerFragment")
                        .addToBackStack("MyCustomerFragment").commit();

            }
        });

        discountCoupons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DisLandingFragment discountfragment  = new DisLandingFragment();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(((ViewGroup)getView().getParent()).getId(), discountfragment, "DiscountFragment")
                        .addToBackStack("DiscountFragment").commit();
            }
        });

        return view;
    }
}