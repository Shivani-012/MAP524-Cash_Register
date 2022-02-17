package com.example.map524_cashregister;

public class Product {

    // declare variables for product name, quantity and price
    private String name;
    private int quantity;
    private double price;

    // default constructor
    Product(){
        name = null;
        quantity = 0;
        price= 0.00;
    }

    // constructor that accepts all fields as a parameter
    Product(String n, int q, double p){
        name = n;
        quantity = q;
        price = p;
    }

    // getter for product name
    String getName() {
        return name;
    }

    // setter for product name
    void setName(String n){
        name = n;
    }

    // getter for product quantity
    int getQuantity() {
        return quantity;
    }

    // setter for product quantity
    void setQuantity(int q){
        quantity = q;
    }

    // getter for product price
    double getPrice() {
        return price;
    }

    // setter for product price
    void setPrice(double p){
        price = p;
    }
}
