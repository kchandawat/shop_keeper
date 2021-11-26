package com.marquedo.marquedo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Store {

    List<String> businessCategory;
    String contactNumber;
    String licenseNumber;
    String membershipType;
    String ownerName;
    Map<String, Object> shopAddress;
    String shopName;
    Map<String, WeekDays> timings;

    public List<String> getBusinessCategory() {
        return businessCategory;
    }

    public void setBusinessCategory(List<String> businessCategory) {
        this.businessCategory = businessCategory;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public Map<String, Object> getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(Map<String, Object> shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopName() {
        return shopName;
    }

    public Map<String, WeekDays> getTimings() {
        return timings;
    }

    public void setTimings(Map<String, WeekDays> timings) {
        this.timings = timings;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
