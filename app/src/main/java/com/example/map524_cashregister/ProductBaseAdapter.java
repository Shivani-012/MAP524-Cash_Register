package com.example.map524_cashregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductBaseAdapter extends BaseAdapter {

    // declare array list of products and context
    ArrayList<Product> listOfProducts;
    Context context;

    // constructor that accepts all fields as parameters
    public ProductBaseAdapter(ArrayList<Product> listOfProducts, Context context){
        this.listOfProducts = listOfProducts;
        this.context = context;
    }

    // method that returns the size of the array list of products
    @Override
    public int getCount() {
        return listOfProducts.size();
    }

    // method that gets a Product from the array list at the index of the parameter
    @Override
    public Object getItem(int i) {
        return listOfProducts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    // method that returns the inflated view of one row for Products table
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        // initialize view as base adapter product row
        view = LayoutInflater.from(context).inflate(R.layout.base_adapter_product_row, null);

        // initialize and set values of product name, quantity and price text views
        TextView productText = view.findViewById(R.id.row_product);
        productText.setText(listOfProducts.get(i).getName());
        TextView qtyText = view.findViewById(R.id.row_qty);
        qtyText.setText(String.valueOf(listOfProducts.get(i).getQuantity()));
        TextView priceText = view.findViewById(R.id.row_price);
        priceText.setText(listOfProducts.get(i).getPriceString());

        return view; // return view
    }
}
