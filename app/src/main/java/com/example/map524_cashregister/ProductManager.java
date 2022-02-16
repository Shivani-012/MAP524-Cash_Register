package com.example.map524_cashregister;

import java.util.ArrayList;

public class ProductManager {

    private ArrayList allProducts = new ArrayList(0);

    ProductManager(Product[] products, int length){
        for (int i = 0; i < length; i++){
            this.addProduct(products[i]);
        }
    }

    public ArrayList getAllProducts(){
        return allProducts;
    }

    public void addProduct(Product p){
        allProducts.add(p);
    }

}
