package com.marquedo.marquedo.ui.ui_3_Orders_Enquiries.Enquiries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class EnquiriesDataModel {

    public String id;
    public String date;
    public String time;
    public String status;
    public String billTotal;
    public String products;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBillTotal() {
        return billTotal;
    }

    public void setBillTotal(String billTotal) {
        this.billTotal = billTotal;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    EnquiriesDataModel(JSONObject object){
        try {
            this.id = object.getString("orderID");
            this.date = object.getString("date");
            this.time = object.getString("time");
            this.products = object.getString("product");
            this.billTotal = object.getString("billtotal");
            this.status = object.getString("status");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<EnquiriesDataModel> fromJson(JSONArray jsonObjects) {
        ArrayList<EnquiriesDataModel> orders = new ArrayList<EnquiriesDataModel>();
        for (int i = 0; i < jsonObjects.length(); i++) {
            try {
                orders.add(new EnquiriesDataModel(jsonObjects.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return orders;
    }
}
