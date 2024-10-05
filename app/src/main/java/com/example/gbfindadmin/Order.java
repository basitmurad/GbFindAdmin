package com.example.gbfindadmin;

import java.util.ArrayList;

public class Order {
    private String orderId;
    private String accountName;
    private String accountNumber;
    private String address;
    private String Status;
    private String totalPrice;
    private ArrayList<Item> items;

    public Order() {
    }

    public Order(String orderId, String accountName, String accountNumber, String address, String status, String totalPrice, ArrayList<Item> items) {
        this.orderId = orderId;
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.address = address;
        Status = status;
        this.totalPrice = totalPrice;
        this.items = items;
    }

    public Order(String orderId, String accountName, String accountNumber, String address, String status, ArrayList<Item> items) {
        this.orderId = orderId;
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.address = address;
        Status = status;
        this.items = items;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    // Getters
    public String getOrderId() {
        return orderId;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    // Inner Item Class
    public static class Item {
        private String itemId;
        private String name;
        private double price;
        private int quantity;

        public Item(String itemId, String name, double price, int quantity) {
            this.itemId = itemId;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        // Getters
        public String getItemId() {
            return itemId;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public int getQuantity() {
            return quantity;
        }
    }
}
