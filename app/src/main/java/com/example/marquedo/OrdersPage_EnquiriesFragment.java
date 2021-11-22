package com.example.marquedo;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrdersPage_EnquiriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrdersPage_EnquiriesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OrdersPage_EnquiriesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrdersPage_EnquiriesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrdersPage_EnquiriesFragment newInstance(String param1, String param2) {
        OrdersPage_EnquiriesFragment fragment = new OrdersPage_EnquiriesFragment();
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
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_orders_page__enquiries, container, false);
        RecyclerView rvOrders = (RecyclerView) view.findViewById(R.id.rvEnquiries_Orderpage);
        ContactsAdapter adapter = new ContactsAdapter();
        rvOrders.setAdapter(adapter);
        rvOrders.setLayoutManager(new LinearLayoutManager(this.getContext()));
        return view;

    }

    public class ContactsAdapter extends
            RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

        // Provide a direct reference to each of the views within a data item
        // Used to cache the views within the item layout for fast access
        public  class ViewHolder extends RecyclerView.ViewHolder {
            // Your holder should contain a member variable
            // for any view that will be set as you render a row


            // We also create a constructor that accepts the entire item row
            // and does the view lookups to find each subview
            public ViewHolder(View itemView) {
                // Stores the itemView in a public final member variable that can be used
                // to access the context from any ViewHolder instance.
                super(itemView);

            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            // Inflate the custom layout
            View ordersView = inflater.inflate(R.layout.orderspage_order_tile, parent, false);

            // Return a new holder instance
            ContactsAdapter.ViewHolder viewHolder = new ContactsAdapter.ViewHolder(ordersView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 3;
        }



        // Involves populating data into the item through holder

    }

    // Returns the total count of items in the list
}