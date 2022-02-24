package com.marquedo.marquedo;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

//import androidx.fragment.app.Fragment;

public class Step3Fragment extends Fragment {

    View view, view2;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
// Inflate the layout for this fragment
        view = inflater.inflate(R.layout.marketing_campaign_step3_fragment, container, false);
        //view2 = inflater.inflate(R.id.contact_category, container, false);
        //final AutoCompleteTextView customerContact = (AutoCompleteTextView) view.findViewById(R.id.contact_category);

        ArrayList<String> categories = new ArrayList<>();
        categories.add("All Customers");
        categories.add("Subscribed");
        categories.add("Past Customers");
        categories.add("Individuals");
        int selectedItem = -1;
        Spinner customerContact = (Spinner)  view.findViewById(R.id.contact_category);
        //customerContact.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.contacts_spinner_item,categories);
        adapter.setDropDownViewResource(R.layout.contacts_spinner_item);
        customerContact.setAdapter(adapter);

        customerContact.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //view.setBackgroundColor(getResources().getColor(R.color.yellow));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //customerContact.setPopupBackgroundResource(R.color.white);
            }
        });

        MaterialButton importcontacts = (MaterialButton) view.findViewById(R.id.import_contacts);
        importcontacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent import_contacts = new Intent(getContext(), MarketingCampaignContacts.class);
                startActivity(import_contacts);

            }
        });
        //initCustomerCategory();
        return view;
    }
    private ArrayList<String> getContactCategory(){
        ArrayList<String> categories = new ArrayList<>();
        categories.add("All Customers");
        categories.add("Subscribed");
        categories.add("Past Customers");
        categories.add("Individuals");
        return categories;

    }
    /*private void initCustomerCategory(){
        final AutoCompleteTextView customerContact = (AutoCompleteTextView) getView().findViewById(R.id.contact_category);
        ArrayList<String> categoryList = getContactCategory();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,categoryList);
        customerContact.setAdapter(adapter);
    }
    private ArrayList<String> getContactCategory(){
        ArrayList<String> categories = new ArrayList<>();
        categories.add("All Customers");
        categories.add("Subscribed");
        categories.add("Past Customers");
        categories.add("Individuals");
        return categories;

    }*/

}
