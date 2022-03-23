package com.marquedo.marquedo.ProductsNCategories.Product;

import static com.marquedo.marquedo.InternetCheck.getConnectivityStatusString;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;
import com.marquedo.marquedo.InternetCheck;
import com.marquedo.marquedo.R;
import com.marquedo.marquedo.Snack;

public class ProductsFragment extends Fragment
{


    private RecyclerView recyclerView;
    private FrameLayout frameLayout;
    private FirebaseFirestoreSettings settings;
    //private ArrayList<ProductModelClass> prodlist;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    //private ProductListAdapter adapter;
    private ProductListAdapter1 adapter1;



    public ProductsFragment()
    {

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
        View v = inflater.inflate(R.layout.productsncategories_fragment_products, container, false);
        frameLayout = v.findViewById(R.id.fragment_products);
        recyclerView = v.findViewById(R.id.products_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setItemAnimator(null);
        //prodlist = new ArrayList<>();
        //adapter = new ProductListAdapter(prodlist); //, item -> startActivity(new Intent(getContext(), update_product.class)));
        //recyclerView.setAdapter(adapter);
        settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();

        db.setFirestoreSettings(settings);



        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService (Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo == null || !networkInfo.isConnected() || !networkInfo.isAvailable())
        {
            Snackbar snackbar = Snackbar.make(frameLayout, "You are offline", Snackbar.LENGTH_INDEFINITE)
                    .setBackgroundTint(this.getResources().getColor(R.color.black))
                    .setTextColor(this.getResources().getColor(R.color.white));

            snackbar.show();

        }
        else
        {
            Snackbar snackbar = Snackbar.make(frameLayout, "Back Online", Snackbar.LENGTH_LONG)
                    .setBackgroundTint(this.getResources().getColor(R.color.green))
                    .setTextColor(this.getResources().getColor(R.color.white));

            snackbar.show();
        }

        //NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        //NetworkInfo mobConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);


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

