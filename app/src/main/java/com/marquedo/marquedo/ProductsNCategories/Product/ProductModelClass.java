package com.marquedo.marquedo.ProductsNCategories.Product;


public class ProductModelClass
{

    String Image_Primary, Name, Unit_Measure;
    Integer Discount_Price, Number_of_Units,Price;

    public ProductModelClass()
    {

    }

    public ProductModelClass(String image_Primary, String name, String unit_Measure, Integer discount_Price, Integer number_of_Units, Integer price) {
        Image_Primary = image_Primary;
        Name = name;
        Unit_Measure = unit_Measure;
        Discount_Price = discount_Price;
        Number_of_Units = number_of_Units;
        Price = price;
    }


    public String getImage_Primary() {
        return Image_Primary;
    }

    public void setImage_Primary(String image_Primary) {
        Image_Primary = image_Primary;
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
}
