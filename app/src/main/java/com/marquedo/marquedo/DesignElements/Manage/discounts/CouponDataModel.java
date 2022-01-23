package com.marquedo.marquedo.DesignElements.Manage.discounts;

public class CouponDataModel {
    String couponCode;
    boolean couponStatus;
    int usageCount;
    int salesCount;
    String couponOffer;

    public CouponDataModel(String couponCode, String couponOffer) {
        this.couponCode = couponCode;
        this.couponStatus = true;
        this.usageCount = 0;
        this.salesCount = 0;
        this.couponOffer = couponOffer;
    }

    public CouponDataModel(String couponCode, boolean couponStatus,
                           int usageCount, int salesCount, String couponOffer) {
        this.couponCode = couponCode;
        this.couponStatus = couponStatus;
        this.usageCount = usageCount;
        this.salesCount = salesCount;
        this.couponOffer = couponOffer;
    }
}
