package com.example.map524_cashregister;

import java.text.NumberFormat;
import java.util.Date;

public class Purchase {

    // declare product name, quantity, total, and date fields
    private String productName;
    private int quantity;
    private double totalPrice;
    private Date purchaseDate;

    // default constructor
    Purchase(){
        productName = null;
        quantity = 0;
        totalPrice = 0;
        purchaseDate = null;
    }

    // overloaded constructor
    Purchase(String n, int q, double tp, Date d){
        productName = n;
        quantity = q;
        totalPrice = tp;
        purchaseDate = d;
    }

    // getter for product name
    public String getName() {
        return productName;
    }

    // setter for product name
    public void setName(String name) {
        this.productName = name;
    }

    // getter for product quantity
    public int getQuantity() {
        return quantity;
    }

    // setter for product quantity
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // getter for total price of purchase
    public String getTotalPrice() {
        // set number format for currency
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return String.valueOf(formatter.format(totalPrice));
    }

    // setter for total price
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    // getter for purchase date
    public Date getPurchaseDate() {
        return purchaseDate;
    }

    // setter for purchase date
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    // Method that checks if a product is selected and the quantity is valid (more than 0)
    public boolean isValid(){
        if(this.getName()==null || this.getQuantity() <= 0){
            return false;
        }
        return true;
    }
}
