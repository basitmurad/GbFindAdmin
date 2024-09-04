package com.example.gbfindadmin.vendorMode.models;

public class UserClass {
    private String email ,ownerName , ownerShopName , ownerPassword  ,
            vendorId,shopAddress , ownerContact , adminFcm ,ownerAccountName ,ownerAccountNo;


    public UserClass(String email, String ownerName, String ownerShopName, String ownerPassword, String vendorId, String shopAddress, String ownerContact, String adminFcm, String ownerAccountName, String ownerAccountNo) {
        this.email = email;
        this.ownerName = ownerName;
        this.ownerShopName = ownerShopName;
        this.ownerPassword = ownerPassword;
        this.vendorId = vendorId;
        this.shopAddress = shopAddress;
        this.ownerContact = ownerContact;
        this.adminFcm = adminFcm;
        this.ownerAccountName = ownerAccountName;
        this.ownerAccountNo = ownerAccountNo;
    }

    public String getOwnerAccountName() {
        return ownerAccountName;
    }

    public void setOwnerAccountName(String ownerAccountName) {
        this.ownerAccountName = ownerAccountName;
    }

    public String getOwnerAccountNo() {
        return ownerAccountNo;
    }

    public void setOwnerAccountNo(String ownerAccountNo) {
        this.ownerAccountNo = ownerAccountNo;
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

    public String getOwnerPassword() {
        return ownerPassword;
    }

    public void setOwnerPassword(String ownerPassword) {
        this.ownerPassword = ownerPassword;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
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

    public String getAdminFcm() {
        return adminFcm;
    }

    public void setAdminFcm(String adminFcm) {
        this.adminFcm = adminFcm;
    }
}