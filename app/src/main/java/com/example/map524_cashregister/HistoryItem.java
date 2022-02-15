package com.example.map524_cashregister;

import java.util.Date;

public class HistoryItem {

    private String name;
    private int quantity;
    private double totalPrice;
    private Date purchaseDate;

    HistoryItem(String n, int q, double tp, Date d){
        name = n;
        quantity = q;
        totalPrice = tp;
        purchaseDate = d;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return totalPrice;
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
}
