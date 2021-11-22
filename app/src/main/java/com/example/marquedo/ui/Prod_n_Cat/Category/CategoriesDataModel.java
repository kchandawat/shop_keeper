package com.example.marquedo.ui.Prod_n_Cat.Category;

public class CategoriesDataModel {

    boolean availability;
    String title;
    String sub;

    // TODO: Implement Methods for Loading Images from URLS into the ImageView
    public CategoriesDataModel(String categoryTitle, String category_sub) {
        this.title = categoryTitle;
        this.sub = category_sub;
        this.availability = true;
    }

    public CategoriesDataModel(String categoryTitle, String category_sub, boolean availability) {
        this.title = categoryTitle;
        this.sub = category_sub;
        this.availability = availability;
    }

}
