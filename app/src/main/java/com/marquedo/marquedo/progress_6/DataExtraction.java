package com.marquedo.marquedo.progress_6;

import java.util.List;

public class DataExtraction {

    String sizeName;
    String price;
    String discountedPrice;

    List<ColourData> colourData;

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

    public List<ColourData> getColourData() {
        return colourData;
    }

    public void setColourData(List<ColourData> colourData) {
        this.colourData = colourData;
    }
}
