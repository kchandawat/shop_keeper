package com.marquedo.marquedo.OrdersNEnquiries.Orders;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.marquedo.marquedo.R;

import java.util.ArrayList;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.ViewHolder> {

    ArrayList<OrdersDataModel> data = new ArrayList<>();

    public OrderListAdapter(ArrayList<OrdersDataModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public OrderListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OrderListAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ordersnenquiries_list_tile, null));
    }

    @Override
    public void onBindViewHolder(@NonNull OrderListAdapter.ViewHolder holder, int position) {

        holder.Orderid.setText(data.get(position).getId());
        holder.date.setText(data.get(position).getDate());
        holder.time.setText(String.valueOf(data.get(position).getTime()));
        holder.products.setText(String.valueOf(data.get(position).getProducts()));
        holder.billTotal.setText(String.valueOf(data.get(position).getBillTotal()));
        if(data.get(position).getStatus().equals("Pending"))
        {
            holder.cvstatus.setVisibility(View.INVISIBLE);
            holder.pendingstatus.setVisibility(View.VISIBLE);
            holder.acceptedstatus.setVisibility(View.INVISIBLE);
            holder.rejectbtn.setVisibility(View.INVISIBLE);
        }
        else if(data.get(position).getStatus().equals("Accepted"))
        {
            holder.cvstatus.setVisibility(View.INVISIBLE);
            holder.pendingstatus.setVisibility(View.INVISIBLE);
            holder.acceptedstatus.setVisibility(View.VISIBLE);
            holder.rejectbtn.setVisibility(View.INVISIBLE);
        }
        if(data.get(position).getStatus().equals("Notify"))
        {
            holder.cvstatus.setVisibility(View.VISIBLE);
            holder.pendingstatus.setVisibility(View.INVISIBLE);
            holder.acceptedstatus.setVisibility(View.INVISIBLE);
        holder.rejectbtn.setVisibility(View.INVISIBLE);}


        holder.vieworderdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), Order_details_overview.class);
                view.getContext().startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    //TODO: Implement Availability Factor
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Orderid, date, time, products, billTotal, pendingstatus, acceptedstatus, vieworderdetails;
        Button confirmbtn, rejectbtn;
        CardView cvstatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Orderid = itemView.findViewById(R.id.tVOrderID);
            date = itemView.findViewById(R.id.tvDate);
            time = itemView.findViewById(R.id.tVTime);
            products = itemView.findViewById(R.id.tvProducts);
            billTotal = itemView.findViewById(R.id.tvBillTotal);
            pendingstatus = itemView.findViewById(R.id.tvstatusPending);
            acceptedstatus = itemView.findViewById(R.id.tvstatusAccepted);
            cvstatus = itemView.findViewById(R.id.cvstatus);
            rejectbtn = itemView.findViewById(R.id.btngrey);
            vieworderdetails = itemView.findViewById(R.id.tvViewDetails);
        }
    }
}
