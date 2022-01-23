package com.marquedo.marquedo.Services;

public class ServiceDataModel {

    boolean availability;
    String title;

    // TODO: Implement Methods for Loading Images from URLS into the ImageView
    public ServiceDataModel(String serviceTitle) {
        this.title = serviceTitle;
        this.availability = true;
    }

    public ServiceDataModel(String serviceTitle, boolean availability) {
        this.title = serviceTitle;
        this.availability = availability;
    }

}
