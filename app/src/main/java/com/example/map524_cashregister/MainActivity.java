package com.example.map524_cashregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements View.OnClickListener{

    // declare activity view elements
    TextView product_text;
    TextView qty_text;
    TextView total_text;

    Button manager_btn;
    Button buy_btn;

    NumberPicker qty_picker;

    ListView product_list;

    ProductManager productManager;
    ArrayList productList;
    Product selectedProduct;
    Purchase mainPurchase;
    HistoryManager historyManager;

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        builder = new AlertDialog.Builder(this);

        productManager = ((MyApp)getApplication()).productList;
        productList = productManager.getAllProducts();
        //productList = ((MyApp)getApplication()).productList.getAllProducts();
        selectedProduct = ((MyApp)getApplication()).selectedProduct;

        mainPurchase = ((MyApp)getApplication()).mainPurchase;
        historyManager = ((MyApp)getApplication()).historyList;

        // ??
        product_text = findViewById(R.id.productText);
        product_text.setText(selectedProduct.getName()==null ? "Product" : selectedProduct.getName());

        qty_text = findViewById(R.id.quantityText);
        qty_text.setText(String.valueOf(mainPurchase.getQuantity()));

        total_text = findViewById(R.id.totalText);
        total_text.setText(String.valueOf(mainPurchase.getTotalPrice()));


        manager_btn = findViewById(R.id.managerBtn);
        manager_btn.setOnClickListener(this);
        buy_btn = findViewById(R.id.buyBtn);
        buy_btn.setOnClickListener(this);

        qty_picker = findViewById(R.id.numPicker);
        qty_picker.setValue(mainPurchase.getQuantity());
        qty_picker.setOnClickListener(this);

        if (qty_picker != null) {
            qty_picker.setMinValue(0);
            qty_picker.setMaxValue(100);
            qty_picker.setWrapSelectorWheel(true);
            qty_picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldQty, int newQty) {
                    Log.e("test", "e changed to" + newQty);

                    mainPurchase.setQuantity(newQty);
                    qty_text.setText(String.valueOf(newQty));

                    mainPurchase.setTotalPrice(selectedProduct.getPrice() * newQty);
                    total_text.setText(mainPurchase.getTotalPrice());

                    // update quantity text
                    // update history object
                    // update total

                    //String text = "Changed from " + oldVal + " to " + newVal;
                    //Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                }
            });
        }


        product_list = findViewById(R.id.productList);
        ProductBaseAdapter productAdapter = new ProductBaseAdapter(productList, this);
        product_list.setAdapter(productAdapter);

        product_list.setOnItemClickListener(messageClickedHandler);

    }

    // Create a message handling object as an anonymous class.
    private AdapterView.OnItemClickListener messageClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            // Do something in response to the click
            Log.d("test", "item clicked; id: " + id + " pos: " + position);
            resetValues();
            selectedProduct = (Product)productList.get(position);
            mainPurchase.setName(selectedProduct.getName());
            product_text.setText(mainPurchase.getName());
        }
    };

    public boolean validatePurchase(){
        if(!mainPurchase.isValid()){
            Toast.makeText(this,"All fields are required!",Toast.LENGTH_LONG).show();
            return false;
        }
        else if(selectedProduct.getQuantity() < mainPurchase.getQuantity()){
            Toast.makeText(this,"Not enough quantity in stock!",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public void resetValues(){

        // clear mainPurchase by setting it to a new purchase
        //((MyApp)getApplication()).mainPurchase = new Purchase();
        mainPurchase = new Purchase();
        selectedProduct = new Product();

        product_text.setText(selectedProduct.getName()==null ? "Product" : selectedProduct.getName());
        qty_text.setText(String.valueOf(mainPurchase.getQuantity()));
        total_text.setText(mainPurchase.getTotalPrice());

        qty_picker.setValue(mainPurchase.getQuantity());
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch(id){
            // if manager button is click, open manager panel activity
            case R.id.managerBtn:
                // open manager activity
                Intent myIntent = new Intent(this, ManagerPanelActivity.class);
                startActivity(myIntent);
                break;

            // if buy button is clicked...
            case R.id.buyBtn:
                // validate that:
                //      1) product & quantity are selected
                //      2) selected quantity is not over current available quantity
                if(validatePurchase()){

                    // display pop-up that purchase was successful
                    builder.setTitle("Thank you for your Purchase!");
                    builder.setMessage("Your purchase of " + mainPurchase.getQuantity() + " "
                            + mainPurchase.getName() + " for " + mainPurchase.getTotalPrice());
                    builder.show();
                    builder.setCancelable(true);

                    // add order to history list
                    historyManager.addPurchase(mainPurchase);

                    // update quantity of product
                    int productPos = productManager.getProductPos(mainPurchase.getName());
                    productManager.getAllProducts().get(productPos).setQuantity(productManager.getAllProducts().get(productPos).getQuantity() - mainPurchase.getQuantity());
                    ProductBaseAdapter productAdapter = new ProductBaseAdapter(productList, this);
                    product_list.setAdapter(productAdapter);

                    // reset all values
                    resetValues();

                }
                break;
        }

    }
}