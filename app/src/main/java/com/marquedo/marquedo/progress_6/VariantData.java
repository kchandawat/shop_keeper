package com.marquedo.marquedo.progress_6;

import java.util.ArrayList;
import java.util.List;

public class VariantData {
    String sizeName;
    String price;
    String discountedPrice;

    List<ColourCodes> colourCodes;

    public List<ColourCodes> getColourCodes() {
        return colourCodes;
    }

    public void setColourCodes(List<ColourCodes> colourCodes) {
        this.colourCodes = colourCodes;
    }

    public VariantData(String sizeName, String price, String discountedPrice) {
        this.sizeName = sizeName;
        this.price = price;
        this.discountedPrice = discountedPrice;
    }

    public VariantData() {

    }

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