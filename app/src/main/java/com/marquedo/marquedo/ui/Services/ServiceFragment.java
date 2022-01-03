package com.marquedo.marquedo.ui.Services;

import android.content.Intent;
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
import com.marquedo.marquedo.secondary.PnS.AddProductNServiceActivity;

import java.util.ArrayList;

public class ServiceFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ArrayList<ServiceDataModel> data = new ArrayList<>();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ServiceFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrdersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ServiceFragment newInstance(String param1, String param2) {
        ServiceFragment fragment = new ServiceFragment();
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


        data.add(new ServiceDataModel("Mock Service Title (Filler, Filler, Filler)"));
        data.add(new ServiceDataModel("Mock Service Title 2 (Filler, Filler)",
                false));
        data.add(new ServiceDataModel("Gadgets, Mobiles, Tablets and Acccessories Repair",
                true));
        data.add(new ServiceDataModel("Gadgets, Mobiles, Tablets and Acccessories Repair",
                true));
        data.add(new ServiceDataModel("Gadgets, Mobiles, Tablets and Acccessories Repair",
                false));
        data.add(new ServiceDataModel("Mock Service Title (Filler, Filler, Filler)",
                true));
        data.add(new ServiceDataModel("Mock Service Title 2 (Filler, Filler)",
                false));
        data.add(new ServiceDataModel("Mock Service Title (Filler, Filler, Filler)",
                true));
        data.add(new ServiceDataModel("Mock Service Title 2 (Filler, Filler)",
                false));
        data.add(new ServiceDataModel("Mock Service Title (Filler, Filler, Filler)",
                true));
        data.add(new ServiceDataModel("Mock Service Title 2 (Filler, Filler)",
                false));
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_services, container, false);

        view.findViewById(R.id.add_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddProductNServiceActivity.class);
                intent.putExtra("frgToLoad", "Service");
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.services_list);
        recyclerView.setAdapter(new ServiceListAdapter(data));
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false));
    }

}
