package com.example.map524_cashregister;

public class Product {

    private String name;
    private int quantity;
    private double price;

    Product(String n, int q, double p){
        name = n;
        quantity = q;
        price = p;
    }

    String getName() {
        return name;
    }

    void setName(String n){
        name = n;
    }

    int getQuantity() {
        return quantity;
    }

    void setName(int q){
        quantity = q;
    }

    double getPrice() {
        return price;
    }

    void setPrice(double p){
        price = p;
    }

}