package com.example.map524_cashregister;

import android.app.Application;

public class MyApp extends Application {

    Product pants = new Product("Pants", 35, 24.99);
    Product shoes = new Product("Shoes", 100, 59.99);
    Product hats = new Product("Hats", 23, 7.50);

    Product[] products = {pants, shoes, hats};

    ProductManager productList = new ProductManager(products, products.length);

    HistoryManager historyList = new HistoryManager();
    Purchase mainPurchase = new Purchase();

}
