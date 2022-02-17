package com.example.map524_cashregister;

import java.text.NumberFormat;
import java.util.Date;

public class Purchase {

    private String productName;
    private int quantity;
    private double totalPrice;
    private Date purchaseDate;

    Purchase(){
        productName = null;
        quantity = 0;
        totalPrice = 0;
        purchaseDate = null;
    }

    Purchase(String n, int q, double tp, Date d){
        productName = n;
        quantity = q;
        totalPrice = tp;
        purchaseDate = d;
    }

    public String getName() {
        return productName;
    }

    public void setName(String name) {
        this.productName = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTotalPrice() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        return String.valueOf(formatter.format(totalPrice));
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

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
