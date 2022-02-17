package com.example.map524_cashregister;

import android.app.Application;

public class MyApp extends Application {

    // declare and initialize default products for app
    Product pants = new Product("Shirt", 35, 19.99);
    Product shoes = new Product("Belt", 100, 7.50);
    Product hats = new Product("Sweater", 23, 24.99);
    Product jeans = new Product("Jeans", 40, 59.99);

    Product[] products = {pants, shoes, hats, jeans}; // create an array of products

    // declare and initialize productManager with array of products
    ProductManager productList = new ProductManager(products);

    // declare a HistoryManager, Purchase, and Product as new objects
    HistoryManager historyList = new HistoryManager();
    Purchase mainPurchase = new Purchase();
    Product selectedProduct = new Product();

}
