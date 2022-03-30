package com.marquedo.marquedo.Home;

import java.util.List;

public class ServiceNameModelClass
{
    String Image_Primary, name,category, measure, details;
    Integer discount_price, number_of_hours ,price;
    List<String> images;

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public ServiceNameModelClass(String image_Primary, String name, String category, String measure, String details, Integer discount_price, Integer number_of_hours, Integer price, List<String> images) {
        Image_Primary = image_Primary;
        this.name = name;
        this.category = category;
        this.measure = measure;
        this.details = details;
        this.discount_price = discount_price;
        this.number_of_hours = number_of_hours;
        this.price = price;
        this.images = images;
    }

    public ServiceNameModelClass()
    {

    }

    public String getImage_Primary() {
        return Image_Primary;
    }

    public void setImage_Primary(String image_Primary) {
        Image_Primary = image_Primary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }


    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(Integer discount_price) {
        this.discount_price = discount_price;
    }

    public Integer getNumber_of_hours() {
        return number_of_hours;
    }

    public void setNumber_of_hours(Integer number_of_hours) {
        this.number_of_hours = number_of_hours;
    }
}
