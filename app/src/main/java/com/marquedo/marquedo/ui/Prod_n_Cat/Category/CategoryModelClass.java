package com.marquedo.marquedo.ui.Prod_n_Cat.Category;

public class CategoryModelClass
{
    String Category, Details,Measure,Name;
    Number DiscountPrice, NumberofProducts, Price;

    public CategoryModelClass()
    {

    }

    public CategoryModelClass(String category, String details, String measure, String name, Number discountPrice, Number numberofProducts, Number price)
    {
        Category = category;
        Details = details;
        Measure = measure;
        Name = name;
        DiscountPrice = discountPrice;
        NumberofProducts = numberofProducts;
        Price = price;
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

    public Number getDiscountPrice() {
        return DiscountPrice;
    }

    public void setDiscountPrice(Number discountPrice) {
        DiscountPrice = discountPrice;
    }

    public Number getNumberofProducts() {
        return NumberofProducts;
    }

    public void setNumberofProducts(Number numberofProducts) {
        NumberofProducts = numberofProducts;
    }

    public Number getPrice() {
        return Price;
    }

    public void setPrice(Number price) {
        Price = price;
    }
}
