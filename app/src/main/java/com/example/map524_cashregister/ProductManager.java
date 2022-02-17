package com.example.map524_cashregister;

import java.util.ArrayList;

public class ProductManager {

    // declare array list of products
    private ArrayList<Product> allProducts = new ArrayList<>();

    // constructor that accepts an array of products and pushes each into the list of products
    ProductManager(Product[] products){
        for (Product product : products) {
            this.addProduct(product);
        }
    }

    // method that returns array list of all products
    public ArrayList<Product> getAllProducts(){
        return allProducts;
    }

    // method that adds a new product to the array of products
    public void addProduct(Product p){
        allProducts.add(p);
    }

    // method that returns the position of the product with a name matching the String parameter
    public int getProductPos(String product) {
        for(int i = 0; i< allProducts.size(); i++){
            if(product.equals(allProducts.get(i).getName())){
                return i;
            }
        }
        return -1;
    }
}
