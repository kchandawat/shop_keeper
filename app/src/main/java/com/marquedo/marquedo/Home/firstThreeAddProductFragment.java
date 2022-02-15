package com.marquedo.marquedo.Home;

import static android.content.ContentValues.TAG;
import static com.marquedo.marquedo.OurConstants.BUSINESS_CATEGORY_KEY;
import static com.marquedo.marquedo.OurConstants.BUSINESS_CATEGORY_SIZE_KEY;
import static com.marquedo.marquedo.OurConstants.OTP_PREFERENCES;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.marquedo.marquedo.CategoriesRecyclerViewAdapter;
import com.marquedo.marquedo.CheckboxData;
import com.marquedo.marquedo.R;
import com.marquedo.marquedo.Snack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class firstThreeAddProductFragment extends Fragment
{

    public firstThreeAddProductFragment()
    {
        // Required empty public constructor
    }

    public static firstThreeAddProductFragment newInstance(String param1, String param2)
    {
        firstThreeAddProductFragment fragment = new firstThreeAddProductFragment();
        return fragment;
    }


    //private EditText ProdCategory; //ProdName
    //private MaterialAutoCompleteTextView ProdName;
    private AutoCompleteTextView ProdName;
    private Button Continue;
    DatabaseReference databaseReference;
    private String keys;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.home_fragment_first_three_add_product, container, false);
        ProdName = v.findViewById(R.id.prod_name);
        //ProdCategory = v.findViewById(R.id.prod_category);
        Continue = v.findViewById(R.id.prod_continue_button);


        databaseReference = FirebaseDatabase.getInstance().getReference("Products");

       //listView = v.findViewById(R.id.list_data);
        //recyclerView = v.findViewById(R.id.list_data);

        populateSearch();


        /*ProdCategory.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                View view1=getLayoutInflater().inflate(R.layout.home_fragment_new_product_category,null);
                BottomSheetDialog bottomSheetDialog= new BottomSheetDialog(getContext());
                bottomSheetDialog.setContentView(view1);
                bottomSheetDialog.show();
            }
        }); */

        /*Continue.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                String name = ProdName.getText().toString();
                String category = ProdName.getText().toString();

                Intent intent = new Intent(getContext(), profileSetupAddProductPageActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("category", category);
                startActivity(intent);
            }
        }); */

        return v;
    }

    private void populateSearch()
    {
        ValueEventListener eventListener = new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if(snapshot.exists())
                {
                    ArrayList<String> names = new ArrayList<>();
                    for(DataSnapshot dataSnapshot : snapshot.getChildren())
                    {
                        String prodname = dataSnapshot.child("name").getValue(String.class);
                        keys = dataSnapshot.getKey();
                        names.add(prodname);
                        Log.i("letsdo", keys);
                    }

                    ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,names);
                    ProdName.setThreshold(1);
                    ProdName.setAdapter(adapter);


                    ProdName.setOnItemClickListener(new AdapterView.OnItemClickListener() 
                    {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
                        {
                            Intent intent = new Intent(getContext(), profileSetupAddProductPageActivity.class);
                            Log.i("checkkeyresult", keys);
                            intent.putExtra("key",keys);
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        };

        databaseReference.addListenerForSingleValueEvent(eventListener);
    }

}