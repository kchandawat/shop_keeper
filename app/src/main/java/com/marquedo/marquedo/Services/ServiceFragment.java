package com.marquedo.marquedo.Services;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;
import com.marquedo.marquedo.Home.addProductAndServicesProfilesetupActivity;
import com.marquedo.marquedo.ProductsNCategories.Product.ProductListAdapter1;
import com.marquedo.marquedo.ProductsNCategories.Product.ProductModelClass;
import com.marquedo.marquedo.R;

import java.util.ArrayList;

public class ServiceFragment extends Fragment
{

    private RecyclerView recyclerView;
    private FrameLayout frameLayout;
    private FirebaseFirestoreSettings settings;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ServiceListAdapter1 adapter1;
    private MaterialButton addService;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        {

        }


       /* data.add(new ServiceDataModel("Mock Service Title (Filler, Filler, Filler)"));
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
                false)); */
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.services_fragment_services, container, false);

        addService = view.findViewById(R.id.add_service);

        recyclerView = view.findViewById(R.id.services_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setItemAnimator(null);

        settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();

        db.setFirestoreSettings(settings);



        Query query = db.collectionGroup("services");


        FirestoreRecyclerOptions<ServiceModelClass> options = new FirestoreRecyclerOptions.Builder<ServiceModelClass>()
                .setQuery(query, ServiceModelClass.class)
                .build();

        adapter1 = new ServiceListAdapter1(options); //, item -> startActivity(new Intent(getContext(), update_product.class)));
        recyclerView.setAdapter(adapter1);

        /*view.findViewById(R.id.add_service).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getContext(), addProductAndServicesProfilesetupActivity.class);
                startActivity(intent);
            }
        });*/

        addService.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getActivity(), addProductAndServicesProfilesetupActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    /*@Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.services_list);
        //recyclerView.setAdapter(new ServiceListAdapter(data));
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false));
    }*/

    @Override
    public void onStart()
    {
        super.onStart();
        adapter1.startListening();
    }

    @Override
    public void onStop()
    {
        super.onStop();
        adapter1.stopListening();
    }

}
