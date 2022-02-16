package com.example.map524_cashregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ProductBaseAdapter extends BaseAdapter {

    ArrayList<Product> listOfProducts;
    Context context;

    public ProductBaseAdapter(ArrayList<Product> listOfProducts, Context context){
        this.listOfProducts = listOfProducts;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listOfProducts.size();
    }

    @Override
    public Object getItem(int i) {
        return listOfProducts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = LayoutInflater.from(context).inflate(R.layout.base_adapter_product_row, null);
        TextView productText = view.findViewById(R.id.row_product);
        TextView qtyText = view.findViewById(R.id.row_qty);
        TextView priceText = view.findViewById(R.id.row_price);

        productText.setText(listOfProducts.get(i).getName());
        qtyText.setText(String.valueOf(listOfProducts.get(i).getQuantity()));
        priceText.setText(String.valueOf(listOfProducts.get(i).getPrice()));

        return view;
    }
}
