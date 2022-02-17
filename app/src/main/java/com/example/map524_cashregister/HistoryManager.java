package com.example.map524_cashregister;

import java.util.ArrayList;

public class HistoryManager {

    // declare array list of purchases
    private ArrayList<Purchase> allPurchases = new ArrayList<>();

    // method that adds a purchase to the array list
    public void addPurchase(Purchase purchase){
        allPurchases.add(purchase);
    }

    // method that returns an array list of all purchases
    public ArrayList<Purchase> getAllPurchases() {
        return allPurchases;
    }

}
