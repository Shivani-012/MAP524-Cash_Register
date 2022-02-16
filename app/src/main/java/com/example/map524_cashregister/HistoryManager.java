package com.example.map524_cashregister;

import java.util.ArrayList;

public class HistoryManager {

    // declare array list of purchase
    private ArrayList allPurchases = new ArrayList(0);
    
    public void addPurchase(Purchase purchase){
        allPurchases.add(purchase);
    }

    public ArrayList getAllPurchases(){
        return allPurchases;
    }
    
    
}
