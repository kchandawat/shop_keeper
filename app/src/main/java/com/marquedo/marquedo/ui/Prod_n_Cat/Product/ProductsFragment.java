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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.marquedo.marquedo.R;
import com.marquedo.marquedo.StoreTry;
import com.marquedo.marquedo.update_product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsFragment extends Fragment {


    private RecyclerView recyclerView;
    ArrayList<ProductModelClass> prodlist;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ProductListAdapter adapter;

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
                             Bundle savedInstanceState)
    {

        View v = inflater.inflate(R.layout.fragment_products, container, false);
        recyclerView = v.findViewById(R.id.products_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        prodlist = new ArrayList<>();
        adapter = new ProductListAdapter(prodlist, item -> startActivity(new Intent(getContext(), update_product.class)));
        recyclerView.setAdapter(adapter);

        DocumentReference documentReference = db.collection("Store").document("StoreIDGenerated");
       documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>()
        {
            @Override
            public void onSuccess(@NonNull DocumentSnapshot documentSnapshot)
            {
                //documentReference.getFirestore();
                //Toast.makeText(getActivity(),data, Toast.LENGTH_SHORT).show();
                //Map<String, Object> list1 = documentSnapshot.getData();
                //StoreTry list1 = documentSnapshot.getData();
                //ProductModelClass list = documentSnapshot.get("Mobiles");
                //String name = documentSnapshot.getString("Name");
                //String category = documentSnapshot.getString("Category");
                //String details = documentSnapshot.getString("Details");
                //String measure = documentSnapshot.getString("Measure");
                //String url = documentSnapshot.getString("imageurl1");
                //Integer prodcount = Integer.parseInt(documentSnapshot.getData().get("NumberofProducts").toString());
                //Integer price = Integer.parseInt(documentSnapshot.getData().get("Price").toString());
                //Integer discount = Integer.parseInt(documentSnapshot.getData().get("DiscountPrice").toString());
              Map<String,Object> data = documentSnapshot.getData();

              Map<String,Object> Grocery=(Map<String, Object>) ((Map<String, Object>) data.get("products")).get("Grocery");

                Log.i("hello", Grocery.get("Details").toString());
                ProductModelClass list = new ProductModelClass(Grocery.get("Category").toString(),Grocery.get("Details").toString(),Grocery.get("Measure").toString(),Grocery.get("Name").toString(),Grocery.get("imageurl1").toString(),Integer.parseInt(Grocery.get("DiscountPrice").toString()),Integer.parseInt( Grocery.get("Price").toString()),Integer.parseInt(Grocery.get("NumberofProducts").toString()));

                //Log.d("hello", documentSnapshot.getString("Name"));
                prodlist.add(list);
                //prodlist.add(new ProductModelClass(name,category,details,measure,url,null,null,null));
                adapter.notifyDataSetChanged();
            }

        }).addOnFailureListener(new OnFailureListener() 
        {
            @Override
            public void onFailure(@NonNull Exception e) 
            {
                Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
            }
        });




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
}