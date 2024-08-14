package com.example.gbfindadmin.adminMode.model;

public class VendorClass {
    private String vendorShopName , ownerName , vendorAddress;

    public VendorClass(String vendorShopName, String ownerName, String vendorAddress) {
        this.vendorShopName = vendorShopName;
        this.ownerName = ownerName;
        this.vendorAddress = vendorAddress;
    }

    public VendorClass() {
    }

    public String getVendorShopName() {
        return vendorShopName;
    }

    public void setVendorShopName(String vendorShopName) {
        this.vendorShopName = vendorShopName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }
}
