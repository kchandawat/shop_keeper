package com.example.marquedo.ui.Orders_Enquiries.Orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.marquedo.R;
import com.example.marquedo.models.Orders_details_overview_model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrdersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrdersFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ArrayList<OrdersDataModel> data = new ArrayList<>();
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView recyclerView;

    public OrdersFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrdersFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrdersFragment newInstance(String param1, String param2) {
        OrdersFragment fragment = new OrdersFragment();
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
        JSONObject jsonorder1 = new JSONObject();
        try {
            jsonorder1.put("orderID","ID: 325636");
            jsonorder1.put("date","24/11/21");
            jsonorder1.put("time","06:30pm");
            jsonorder1.put("product","Products: 03");
            jsonorder1.put("billtotal","Bill Total: 1600");
            jsonorder1.put("status","Notify");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject jsonorder2 = new JSONObject();
        try {
            jsonorder2.put("orderID","ID: 325634");
            jsonorder2.put("date","24/11/21");
            jsonorder2.put("time","06:30pm");
            jsonorder2.put("product","Products: 04");
            jsonorder2.put("billtotal","Bill Total: 1600");
            jsonorder2.put("status","Pending");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONObject jsonorder3 = new JSONObject();
        try {
            jsonorder3.put("orderID","ID: 325345");
            jsonorder3.put("date","23/11/21");
            jsonorder3.put("time","02:30pm");
            jsonorder3.put("product","Products: 04");
            jsonorder3.put("billtotal","Bill Total: 1600");
            jsonorder3.put("status","Accepted");

        } catch (JSONException e) {
            e.printStackTrace();
        }


        JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonorder1);
        jsonArray.put(jsonorder2);
        jsonArray.put(jsonorder3);
        data = OrdersDataModel.fromJson(jsonArray);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_orders_page__orders, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.rvOrders_Orderpage);
        recyclerView.setAdapter(new OrderListAdapter(data));
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), RecyclerView.VERTICAL, false));
    }

}