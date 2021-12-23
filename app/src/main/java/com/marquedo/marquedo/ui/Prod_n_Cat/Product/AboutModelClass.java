package com.marquedo.marquedo.ui.Prod_n_Cat.Product;

import java.util.List;

public class AboutModelClass
{
    String Category, Details, Name, Unit_Measure;
    Integer Discount_Price, Number_of_Units,Price;
    List<String> urls;

    public AboutModelClass()
    {

    }

    public AboutModelClass(String category, String details, String name, String unit_Measure, Integer discount_Price, Integer number_of_Units, Integer price, List<String> urls) {
        Category = category;
        Details = details;
        Name = name;
        Unit_Measure = unit_Measure;
        Discount_Price = discount_Price;
        Number_of_Units = number_of_Units;
        Price = price;
        this.urls = urls;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUnit_Measure() {
        return Unit_Measure;
    }

    public void setUnit_Measure(String unit_Measure) {
        Unit_Measure = unit_Measure;
    }

    public Integer getDiscount_Price() {
        return Discount_Price;
    }

    public void setDiscount_Price(Integer discount_Price) {
        Discount_Price = discount_Price;
    }

    public Integer getNumber_of_Units() {
        return Number_of_Units;
    }

    public void setNumber_of_Units(Integer number_of_Units) {
        Number_of_Units = number_of_Units;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }
}
