package com.marquedo.marquedo.Home;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;
import static com.marquedo.marquedo.OurConstants.BUSINESS_CATEGORY_KEY;
import static com.marquedo.marquedo.OurConstants.BUSINESS_CATEGORY_SIZE_KEY;
import static com.marquedo.marquedo.OurConstants.OTP_PREFERENCES;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

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
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.button.MaterialButton;
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


public class firstThreeAddServiceFragment extends Fragment
    {
        public firstThreeAddServiceFragment()
        {

        }

        public static firstThreeAddServiceFragment newInstance(String param1, String param2) {
            firstThreeAddServiceFragment fragment = new firstThreeAddServiceFragment();
            return fragment;
        }

        DatabaseReference databaseReference;
        private AutoCompleteTextView ServiceName;
        private Button Continue;
        private String keys;
        private Snack snack;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState)
        {
            // Inflate the layout for this fragment
            View v = inflater.inflate(R.layout.home_fragment_first_three_add_service, container, false);
            ServiceName = v.findViewById(R.id.service_name);
            Continue = v.findViewById(R.id.service_continue_button);
            snack = new Snack(getContext());

            databaseReference = FirebaseDatabase.getInstance().getReference("Services");

            populateSearch();


            if(ServiceName != null)
            {
                Continue.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        String name = ServiceName.getText().toString();

                        Intent intent = new Intent(getContext(), profileSetupAddServicePageActivity.class);
                        intent.putExtra("name", name);
                        startActivity(intent);
                    }
                });
            }
            else
            {

                snack.snackBar(ServiceName, "Please enter a service name");
            }



            return v;
        }

        private void populateSearch()
        {
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener()
            {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot)
                {
                    if(snapshot.exists())
                    {
                        ArrayList<String> servicename = new ArrayList<>();
                        for(DataSnapshot dataSnapshot : snapshot.getChildren())
                        {
                            String names = dataSnapshot.child("name").getValue(String.class);
                            keys = dataSnapshot.getKey().toString();
                            Log.i("checkKey", keys);
                            servicename.add(names);
                        }
                        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1,servicename);
                        ServiceName.setThreshold(1);
                        ServiceName.setAdapter(adapter);

                        //String key = snapshot.child("id").toString();

                        ServiceName.setOnItemClickListener(new AdapterView.OnItemClickListener()
                        {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                            {
                                //String selection = parent.getItemAtPosition(position).toString();
                                //String selected = adapter.getItem(position).toString();
                                Intent intent = new Intent(getContext(), profileSetupAddServicePageActivity.class);
                                Log.i("checkkeyresult", keys);
                                intent.putExtra("key",keys);
                                startActivity(intent);
                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }