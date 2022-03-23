package com.marquedo.marquedo.Home;

import java.util.List;

public class ProductNameModelClass
{
    String image_primary, name,category, unit_measure, details;
    Integer discount_price, number_of_units ,price;
    List<String> images;

    public ProductNameModelClass()
    {

    }

    public ProductNameModelClass(String image_primary, String name, String category, String unit_measure, String details, Integer discount_price, Integer number_of_units, Integer price, List<String> images) {
        this.image_primary = image_primary;
        this.name = name;
        this.category = category;
        this.unit_measure = unit_measure;
        this.details = details;
        this.discount_price = discount_price;
        this.number_of_units = number_of_units;
        this.price = price;
        this.images = images;
    }

    public String getImage_primary() {
        return image_primary;
    }

    public void setImage_primary(String image_primary) {
        this.image_primary = image_primary;
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

    public String getUnit_measure() {
        return unit_measure;
    }

    public void setUnit_measure(String unit_measure) {
        this.unit_measure = unit_measure;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Integer getDiscount_price() {
        return discount_price;
    }

    public void setDiscount_price(Integer discount_price) {
        this.discount_price = discount_price;
    }

    public Integer getNumber_of_units() {
        return number_of_units;
    }

    public void setNumber_of_units(Integer number_of_units) {
        this.number_of_units = number_of_units;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
