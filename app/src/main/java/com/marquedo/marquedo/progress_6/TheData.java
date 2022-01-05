package com.marquedo.marquedo.progress_6;

import java.util.List;

public class TheData {

    String sizeName;
    String price;
    String discountedPrice;

    String colourCodesList;

    public TheData(String colourList) {
        this.colourCodesList = colourList;
    }

    public TheData() {

    }

    public String getColourCodesList() {
        return colourCodesList;
    }

    public void setColourCodesList(String colourCodesList) {
        this.colourCodesList = colourCodesList;
    }

//    public TheData(String sizeName) {
//        this.sizeName = sizeName;
//    }

    public String getSizeName() {
        return sizeName;
    }

    public void setSizeName(String sizeName) {
        this.sizeName = sizeName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(String discountedPrice) {
        this.discountedPrice = discountedPrice;
    }
}
