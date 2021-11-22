package com.example.marquedo.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Orders_details_overview_model {
    public String productName;
    public String quantity;


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String price;
    public String total;

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    Orders_details_overview_model(JSONObject object){
            try {
                this.productName = object.getString("ProductName");
                this.quantity = object.getString("quantity");
                this.price = object.getString("price");
                this.total = object.getString("total");
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        // Factory method to convert an array of JSON objects into a list of objects
        // User.fromJson(jsonArray);
        public static ArrayList<Orders_details_overview_model> fromJson(JSONArray jsonObjects) {
            ArrayList<Orders_details_overview_model> orders = new ArrayList<Orders_details_overview_model>();
            for (int i = 0; i < jsonObjects.length(); i++) {
                try {
                    orders.add(new Orders_details_overview_model(jsonObjects.getJSONObject(i)));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return orders;
        }
    }

