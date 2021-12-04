package com.marquedo.marquedo.ui.Prod_n_Cat.Category;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.marquedo.marquedo.R;
import com.marquedo.marquedo.ui.Prod_n_Cat.Product.ProductModelClass;

import java.util.ArrayList;


public class CategoriesFragment extends Fragment
{

    private RecyclerView recyclerView;
    ArrayList<CategoryModelClass> catlist;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public CategoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
       /* data.add(new CategoriesDataModel("Mock Category Title (Filler, Filler, Filler)",
                "20 Products"));
        data.add(new CategoriesDataModel("Mock Category Title (Filler, Filler)",
                "110 Products"));
        data.add(new CategoriesDataModel("Mock Category Title",
                "10 Products", false));
        data.add(new CategoriesDataModel("Mock Category Title",
                "120 Products"));
        data.add(new CategoriesDataModel("Mock Category Title",
                "90 Products"));
        data.add(new CategoriesDataModel("Mock Category Title",
                "10 Products", false));
        data.add(new CategoriesDataModel("Mock Category Title",
                "120 Products"));
        data.add(new CategoriesDataModel("Mock Category Title",
                "120 Products"));
        data.add(new CategoriesDataModel("Mock Category Title",
                "120 Products"));
        data.add(new CategoriesDataModel("Mock Category Title",
                "120 Products")); */
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_categories, container, false);
        recyclerView = v.findViewById(R.id.categories_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        catlist = new ArrayList<>();


        return v;
    }


}