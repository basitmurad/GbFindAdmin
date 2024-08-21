package com.example.gbfindadmin.vendorMode.models;

public class UserClass {
    private String email ,ownerName , ownerShopName , ownerPassword ,shopAddress , ownerContact , adminFcm;

    public UserClass(String email, String ownerName, String ownerShopName, String ownerPassword, String shopAddress, String ownerContact, String adminFcm) {

        this.email = email;
        this.ownerName = ownerName;
        this.ownerShopName = ownerShopName;
        this.ownerPassword = ownerPassword;
        this.shopAddress = shopAddress;
        this.ownerContact = ownerContact;
        this.adminFcm = adminFcm;
    }

    public UserClass() {
    }

    public String getAdminFcm() {
        return adminFcm;
    }

    public void setAdminFcm(String adminFcm) {
        this.adminFcm = adminFcm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerShopName() {
        return ownerShopName;
    }

    public void setOwnerShopName(String ownerShopName) {
        this.ownerShopName = ownerShopName;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getOwnerContact() {
        return ownerContact;
    }

    public void setOwnerContact(String ownerContact) {
        this.ownerContact = ownerContact;
    }
}
