package com.example.map524_cashregister;

import java.util.ArrayList;

public class ProductManager {

    private ArrayList<Product> allProducts = new ArrayList(0);

    ProductManager(Product[] products, int length){
        for (int i = 0; i < length; i++){
            this.addProduct(products[i]);
        }
    }

    public ArrayList<Product> getAllProducts(){
        return allProducts;
    }

    public void addProduct(Product p){
        allProducts.add(p);
    }

    public int getProductPos(String product) {
        for(int i = 0; i< allProducts.size(); i++){
            if(product.equals(allProducts.get(i).getName())){
                return i;
            }
        }
        return -1;
    }
}
