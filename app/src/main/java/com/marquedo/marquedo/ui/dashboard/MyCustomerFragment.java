package com.marquedo.marquedo.ui.dashboard;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.marquedo.marquedo.Order_details_overview;
import com.marquedo.marquedo.R;
import com.marquedo.marquedo.models.Orders_details_overview_model;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyCustomerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyCustomerFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyCustomerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyCustomerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyCustomerFragment newInstance(String param1, String param2) {
        MyCustomerFragment fragment = new MyCustomerFragment();
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
        View v = inflater.inflate(R.layout.fragment_my_customer, container, false);
        RecyclerView customers = v.findViewById(R.id.rvMyCustomers);
        ImageButton backBtn = v.findViewById(R.id.btnBackArrow);
        RecyclerAdapter adapter = new RecyclerAdapter();
        customers.setAdapter(adapter);
        customers.setLayoutManager(new LinearLayoutManager(getContext()));

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(getFragmentManager().getBackStackEntryCount());
                System.out.println(getFragmentManager().getFragments());
                System.out.println(getActivity().getClass().getSimpleName());
//                getFragmentManager().popBackStackImmediate();
//                getFragmentManager().popBackStack("ManageFragment", );


            }
        });



        return v;
    }




    public class RecyclerAdapter extends
            RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

        // Provide a direct reference to each of the views within a data item
        // Used to cache the views within the item layout for fast access
        public class ViewHolder extends RecyclerView.ViewHolder {
            // Your holder should contain a member variable
            // for any view that will be set as you render a row
            public TextView tvProductName;
            public TextView tvProductPrice;
            public TextView tvOrderQuantity;
            public TextView tvOrderTotal;

            // We also create a constructor that accepts the entire item row
            // and does the view lookups to find each subview
            public ViewHolder(View itemView) {
                // Stores the itemView in a public final member variable that can be used
                // to access the context from any ViewHolder instance.
                super(itemView);
                tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);
                tvProductPrice = (TextView) itemView.findViewById(R.id.tVProductPrice);
                tvOrderQuantity = (TextView) itemView.findViewById(R.id.tVproductQuantity);
                tvOrderTotal = (TextView) itemView.findViewById(R.id.tVOrderTotal);
            }
        }

        @Override
        public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);

            // Inflate the custom layout
            View myCustomersView = inflater.inflate(R.layout.my_customer_tile, parent, false);

            // Return a new holder instance
            RecyclerAdapter.ViewHolder viewHolder = new RecyclerAdapter.ViewHolder(myCustomersView);
            return viewHolder;
        }

//        private List<myCustomersModel> orders;

//        public RecyclerAdapter(List<Orders_details_overview_model> orders) {
//            this.orders = orders;
//        }

        // Involves populating data into the item through holder
        @Override
        public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
            // Get the data model based on position
//            Orders_details_overview_model order = orders.get(position);

            // Set item views based on your views and data model

//            TextView productName = holder.tvProductName;
//            productName.setText(order.getProductName());
//            TextView productPrice = holder.tvProductPrice;
//            productPrice.setText(order.getPrice());
//            TextView productQuantity = holder.tvOrderQuantity;
//            productQuantity.setText(order.getQuantity());
//            TextView total = holder.tvOrderTotal;
//            total.setText(order.getTotal());

        }

        // Returns the total count of items in the list
        @Override
        public int getItemCount() {
            return 3;
        }
    }


}