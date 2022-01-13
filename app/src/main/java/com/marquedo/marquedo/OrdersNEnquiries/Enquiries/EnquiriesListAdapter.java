package com.marquedo.marquedo.OrdersNEnquiries.Enquiries;

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

public class EnquiriesListAdapter extends RecyclerView.Adapter<EnquiriesListAdapter.ViewHolder> {

    ArrayList<EnquiriesDataModel> enquirydata = new ArrayList<>();

    public EnquiriesListAdapter(ArrayList<EnquiriesDataModel> enquirydata) {
        this.enquirydata = enquirydata;
    }

    @NonNull
    @Override
    public EnquiriesListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EnquiriesListAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ordersnenquiries_list_tile, null));
    }

    @Override
    public void onBindViewHolder(@NonNull EnquiriesListAdapter.ViewHolder holder, int position) {

        holder.Orderid.setText(enquirydata.get(position).getId());
        holder.date.setText(enquirydata.get(position).getDate());
        holder.time.setText(String.valueOf(enquirydata.get(position).getTime()));
        holder.products.setText(String.valueOf(enquirydata.get(position).getProducts()));
        holder.billTotal.setText(String.valueOf(enquirydata.get(position).getBillTotal()));
        if(enquirydata.get(position).getStatus().equals("Pending"))
        {
            holder.cvstatus.setVisibility(View.INVISIBLE);
            holder.pendingstatus.setVisibility(View.VISIBLE);
            holder.acceptedstatus.setVisibility(View.INVISIBLE);
            holder.rejectbtn.setVisibility(View.INVISIBLE);
        }
        else if(enquirydata.get(position).getStatus().equals("Accepted"))
        {
            holder.cvstatus.setVisibility(View.INVISIBLE);
            holder.pendingstatus.setVisibility(View.INVISIBLE);
            holder.acceptedstatus.setVisibility(View.VISIBLE);
            holder.rejectbtn.setVisibility(View.INVISIBLE);
        }
        if(enquirydata.get(position).getStatus().equals("Notify"))
        {
            holder.cvstatus.setVisibility(View.VISIBLE);
            holder.pendingstatus.setVisibility(View.INVISIBLE);
            holder.acceptedstatus.setVisibility(View.INVISIBLE);
            holder.rejectbtn.setVisibility(View.VISIBLE);}

        holder.viewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), Enquiry_details_overview.class);
                view.getContext().startActivity(i);

            }
        });



    }

    @Override
    public int getItemCount() {
        return enquirydata.size();
    }

    //TODO: Implement Availability Factor
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Orderid, date, time, products, billTotal, pendingstatus, acceptedstatus;
        Button rejectbtn;
        CardView cvstatus;
        TextView viewDetails;

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
            viewDetails = itemView.findViewById(R.id.tvViewDetails);

        }
    }
}
