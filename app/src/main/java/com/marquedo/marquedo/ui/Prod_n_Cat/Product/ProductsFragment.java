package com.marquedo.marquedo.ui.Prod_n_Cat.Product;

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
import com.marquedo.marquedo.update_product;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ArrayList<ProductsDataModel> data = new ArrayList<>();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;

    public ProductsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductsFragment newInstance(String param1, String param2) {
        ProductsFragment fragment = new ProductsFragment();
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


        //Mock Data
        data.add(new ProductsDataModel("Mock Product Title (Filler, Filler, Filler)",
                "1 Quantity",
                24000,
                20000));
        data.add(new ProductsDataModel("Mock Product Title (Filler, Filler, Filler)",
                "8 Quantity",
                90000,
                78000));
        data.add(new ProductsDataModel("Mock Product Title",
                "2 Quantity",
                4000,
                2500));
        data.add(new ProductsDataModel("Mock Product Title",
                "2 Quantity",
                4000,
                2500));
        data.add(new ProductsDataModel("Mock Product Title",
                "2 Quantity",
                4000,
                2500));
        data.add(new ProductsDataModel("Mock Product Title",
                "2 Quantity",
                4000,
                2500));
        data.add(new ProductsDataModel("Mock Product Title",
                "2 Quantity",
                4000,
                2500,
                false));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_products, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.products_list);
        recyclerView.setAdapter(new ProductListAdapter(data,
                item -> startActivity(new Intent(getContext(), update_product.class))));
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false));
    }

}