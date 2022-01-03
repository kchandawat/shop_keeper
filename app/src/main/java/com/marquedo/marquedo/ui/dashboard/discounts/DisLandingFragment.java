package com.marquedo.marquedo.ui.dashboard.discounts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.marquedo.marquedo.R;
import com.marquedo.marquedo.ui.Services.ServiceListAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DisLandingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DisLandingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ArrayList<CouponDataModel> data = new ArrayList<>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DisLandingFragment() {
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
    public static DisLandingFragment newInstance(String param1, String param2) {
        DisLandingFragment fragment = new DisLandingFragment();
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

        data.add(new CouponDataModel("FEST50", "Flat 50rs OFF"));
        data.add(new CouponDataModel("FEST150", "Flat 150rs OFF"));
        data.add(new CouponDataModel("FEST50", "Flat 50rs OFF"));
        data.add(new CouponDataModel("FEST150", "Flat 150rs OFF"));
        data.add(new CouponDataModel("FEST50", "Flat 50rs OFF"));
        data.add(new CouponDataModel("FEST150", "Flat 150rs OFF"));
        data.add(new CouponDataModel("FEST50", "Flat 50rs OFF"));
        data.add(new CouponDataModel("FEST150", "Flat 150rs OFF"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dis_landing, container, false);

        view.findViewById(R.id.add_product_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getParentFragmentManager().beginTransaction()
                        .replace(R.id.discount_fragment, new CreateCouponFragment(), "Create Coupon")
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.discounts_list);
        recyclerView.setAdapter(new CouponListAdapter(data));
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false));
    }
}