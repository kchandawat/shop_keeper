package com.marquedo.marquedo.ui.Prod_n_Cat.Product;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.marquedo.marquedo.R;
import com.marquedo.marquedo.update_product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProductsFragment extends Fragment
{


    private RecyclerView recyclerView;
    //private ArrayList<ProductModelClass> prodlist;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    //private ProductListAdapter adapter;
    private ProductListAdapter1 adapter1;

    public ProductsFragment()
    {

    }


    public static ProductsFragment newInstance(String param1, String param2) {
        ProductsFragment fragment = new ProductsFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        /*Mock Data
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
                false));*/

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_products, container, false);
        recyclerView = v.findViewById(R.id.products_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setItemAnimator(null);
        //prodlist = new ArrayList<>();
        //adapter = new ProductListAdapter(prodlist); //, item -> startActivity(new Intent(getContext(), update_product.class)));
        //recyclerView.setAdapter(adapter);

        Query query = db.collectionGroup("products");

        FirestoreRecyclerOptions<ProductModelClass> options = new FirestoreRecyclerOptions.Builder<ProductModelClass>()
                .setQuery(query, ProductModelClass.class)
                .build();

        adapter1 = new ProductListAdapter1(options); //, item -> startActivity(new Intent(getContext(), update_product.class)));
        recyclerView.setAdapter(adapter1);


        /*CollectionReference collectionReference = db.collection("Store");
        db.collectionGroup("products").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>()
        {
            @Override
            public void onSuccess(@NonNull QuerySnapshot queryDocumentSnapshots)
            {
                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot d : list)
                {
                    ProductModelClass productModelClass = d.toObject(ProductModelClass.class);
                    prodlist.add(productModelClass);
                }
                adapter.notifyDataSetChanged();
            }
        });*/
        //DocumentReference documentReference = db.collection("Store").document("StoreIDGenerated");
        /*documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>()
        {
            @Override
            public void onSuccess(@NonNull DocumentSnapshot documentSnapshot)
            {
                ProductModelClass list = documentSnapshot.toObject(ProductModelClass.class);

                prodlist.add(new ProductModelClass());
                adapter.notifyDataSetChanged();
            }

        }).addOnFailureListener(new OnFailureListener() 
        {
            @Override
            public void onFailure(@NonNull Exception e) 
            {
                Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
            }
        }); */
        /*db.collection("Product").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>()
        {
            @Override
            public void onSuccess(@NonNull QuerySnapshot queryDocumentSnapshots)
            {
                List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                for(DocumentSnapshot d : list)
                {
                    ProductModelClass dataList = d.toObject(ProductModelClass.class);
                    prodlist.add(dataList);
                }
                adapter.notifyDataSetChanged();

            }
        });



         */
        /*documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>()
        {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task)
            {
                if(task.isSuccessful())
                {
                    DocumentSnapshot documentSnapshot = task.getResult();
                    String name = documentSnapshot.getString("Name");
                    String Qty = documentSnapshot.getString("NumberofProducts");
                    ArrayList<String> list = new ArrayList<>();
                    list.add(name);
                    list.add(Qty);
                    if(documentSnapshot.exists())
                    {
                    }
                }




            }
        });*/

        return v;

    }

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

