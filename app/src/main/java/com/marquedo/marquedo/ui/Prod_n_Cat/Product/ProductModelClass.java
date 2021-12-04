package com.marquedo.marquedo.ui.Prod_n_Cat.Product;


import com.marquedo.marquedo.ProdTry;

import java.util.List;
import java.util.Map;

public class ProductModelClass extends ProdTry
{
    String Category, Details,Measure,Name, imageurl1;
    Integer DiscountPrice, Price, NumberofProducts;

    public ProductModelClass()
    {

    }

    public ProductModelClass(String category, String details, String measure, String name, String imageurl1, Integer discountPrice, Integer price, Integer numberofProducts) {
        Category = category;
        Details = details;
        Measure = measure;
        Name = name;
        this.imageurl1 = imageurl1;
        DiscountPrice = discountPrice;
        Price = price;
        NumberofProducts = numberofProducts;
    }

    /*public ProductModelClass(String category, String details, String measure, String name, String imageurl1, Integer discountPrice, Integer price, Integer numberofProducts) {
        Category = category;
        Details = details;
        Measure = measure;
        Name = name;
        this.imageurl1 = imageurl1;
        DiscountPrice = discountPrice;
        Price = price;
        NumberofProducts = numberofProducts;
    }*/

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

    public String getMeasure() {
        return Measure;
    }

    public void setMeasure(String measure) {
        Measure = measure;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getDiscountPrice() {
        return DiscountPrice;
    }

    public void setDiscountPrice(Integer discountPrice) {
        DiscountPrice = discountPrice;
    }

    public Integer getPrice() {
        return Price;
    }

    public void setPrice(Integer price) {
        Price = price;
    }

    public Integer getNumberofProducts() {
        return NumberofProducts;
    }

    public void setNumberofProducts(Integer numberofProducts) {
        NumberofProducts = numberofProducts;
    }

    public String getImageurl1() {
        return imageurl1;
    }

    public void setImageurl1(String imageurl1) {
        this.imageurl1 = imageurl1;
    }
}
