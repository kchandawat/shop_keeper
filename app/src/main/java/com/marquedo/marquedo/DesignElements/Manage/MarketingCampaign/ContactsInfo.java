package com.marquedo.marquedo.DesignElements.Manage.MarketingCampaign;

public class ContactsInfo {
    private String contactId;
    private String displayName;
    private String phoneNumber;
    private boolean isSelected;

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    boolean getSelected() {
        return isSelected;
    }

    void setSelected(boolean selected) {
        isSelected = selected;
    }
}