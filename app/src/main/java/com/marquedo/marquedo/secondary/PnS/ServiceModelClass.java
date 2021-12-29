package com.marquedo.marquedo.secondary.PnS;

import java.util.List;

public class ServiceModelClass
{

    String name,category, measure, details;
    Integer discount_Price, number_of_Hours ,price;
    List<String> images;


    public ServiceModelClass()
    {

    }

    public ServiceModelClass(String name, String category, String measure, String details, Integer discount_Price, Integer number_of_Hours, Integer price, List<String> images) {
        this.name = name;
        this.category = category;
        this.measure = measure;
        this.details = details;
        this.discount_Price = discount_Price;
        this.number_of_Hours = number_of_Hours;
        this.price = price;
        this.images = images;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public Integer getDiscount_Price() {
        return discount_Price;
    }

    public void setDiscount_Price(Integer discount_Price) {
        this.discount_Price = discount_Price;
    }

    public Integer getNumber_of_Hours() {
        return number_of_Hours;
    }

    public void setNumber_of_Hours(Integer number_of_Hours) {
        this.number_of_Hours = number_of_Hours;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
