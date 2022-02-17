package com.example.map524_cashregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

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

    // declare objects that will hold product and purchase data
    ProductManager productManager;
    ArrayList<Product> productList;
    Product selectedProduct;
    Purchase mainPurchase;
    HistoryManager historyManager;

    // declare builder for alert dialog box
    AlertDialog.Builder builder;

    // declare base adapter for products
    ProductBaseAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize builder for alert dialog box
        builder = new AlertDialog.Builder(this);

        // initialize variables holding purchase and product data from MyApp
        productManager = ((MyApp)getApplication()).productList;
        productList = productManager.getAllProducts();
        selectedProduct = ((MyApp)getApplication()).selectedProduct;

        mainPurchase = ((MyApp)getApplication()).mainPurchase;
        historyManager = ((MyApp)getApplication()).historyList;

        // initialize product name, quantity and total TextViews and set text using mainPurchase
        product_text = findViewById(R.id.productText);
        product_text.setText(mainPurchase.getName()==null ? "Select a Product" : selectedProduct.getName());

        qty_text = findViewById(R.id.quantityText);
        qty_text.setText(String.valueOf(mainPurchase.getQuantity()));

        total_text = findViewById(R.id.totalText);
        total_text.setText(String.valueOf(mainPurchase.getTotalPrice()));

        // initialize manager and buy buttons and set on click listener
        manager_btn = findViewById(R.id.managerBtn);
        manager_btn.setOnClickListener(this);
        buy_btn = findViewById(R.id.buyBtn);
        buy_btn.setOnClickListener(this);

        // initialize the quantity number picker
        qty_picker = findViewById(R.id.numPicker);
        qty_picker.setValue(mainPurchase.getQuantity());
        qty_picker.setOnClickListener(this);

        if (qty_picker != null) {
            // set picker attributes
            qty_picker.setMinValue(0);
            qty_picker.setMaxValue(100);
            qty_picker.setWrapSelectorWheel(true);
            // if picker value is changed...
            qty_picker.setOnValueChangedListener((picker, oldQty, newQty) -> {
                // set quantity text to new quantity
                qty_text.setText(String.valueOf(newQty));

                // if a product has been selected...
                if (mainPurchase.getName() != null){
                    // update mainPurchase's quantity and set quantity text
                    mainPurchase.setQuantity(newQty);
                    // update mainPurchase's total and set total text
                    mainPurchase.setTotalPrice(selectedProduct.getPrice() * newQty);
                    total_text.setText(mainPurchase.getTotalPrice());
                }
            });
        }

        // initialize product list view
        product_list = findViewById(R.id.productList);

        // declare and initialize base adapter for product list
        productAdapter = new ProductBaseAdapter(productList, this);
        product_list.setAdapter(productAdapter);

        // set on click listener for product list view items
        product_list.setOnItemClickListener(messageClickedHandler);
    }

    // Method that handles list view items clicks
    private AdapterView.OnItemClickListener messageClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            // reset all values to set everything back to default
            resetValues();
            // change selectedProduct to the product in the selected view item
            selectedProduct = productList.get(position);

            // set purchase name and text to the name of the selected product
            mainPurchase.setName(selectedProduct.getName());
            product_text.setText(mainPurchase.getName());

            // loop through list view elements and set the colour back to the default
            for(int a = 0; a < parent.getChildCount(); a++) {
                parent.getChildAt(a).setBackgroundColor(getResources().getColor(R.color.cardview_light_background, null));
            }
            // change the colour of the selected view item
            v.setBackgroundColor(getResources().getColor(R.color.grey, null));
        }
    };

    // method that validates the current main product
    public boolean validatePurchase(){
        // if mainPurchase is NOT valid (checks quantity and name)
        if(!mainPurchase.isValid()){
            // display a toast error and return false
            Toast.makeText(this,"All fields are required!",Toast.LENGTH_LONG).show();
            return false;
        }
        // else if the selected quantity is over the product's available quantity
        else if(selectedProduct.getQuantity() < mainPurchase.getQuantity()){
            // display a toast error and return false
            Toast.makeText(this,"Not enough quantity in stock!",Toast.LENGTH_LONG).show();
            return false;
        }
        return true; // else, return true
    }

    // method that resets all variables and textViews to default
    public void resetValues(){
        // clear mainPurchase by setting it to a new purchase
        mainPurchase = new Purchase();
        // clear selectedProduct by setting it to a new product
        selectedProduct = new Product();

        // reset all TextViews to their defaults with mainPurchase
        product_text.setText(mainPurchase.getName()==null ? "Select a Product" : selectedProduct.getName());
        qty_text.setText(String.valueOf(mainPurchase.getQuantity()));
        total_text.setText(mainPurchase.getTotalPrice());

        // reset the quantity number picker
        qty_picker.setValue(mainPurchase.getQuantity());
    }

    // onClick method for buttons
    @Override
    public void onClick(View view) {

        int id = view.getId(); // get view id

        switch(id){
            // if manager button is click, open manager panel activity
            case R.id.managerBtn:
                // open manager activity
                Intent managerIntent = new Intent(this, ManagerPanelActivity.class);
                startActivity(managerIntent);
                break;

            // if buy button is clicked...
            case R.id.buyBtn:
                // validate that:
                //      1) product & quantity are selected
                //      2) selected quantity is not over current available quantity
                if(validatePurchase()){

                    // display alert that purchase was successful
                    builder.setTitle("Thank you for your Purchase!");
                    builder.setMessage("Your purchase of " + mainPurchase.getQuantity() + " "
                            + mainPurchase.getName() + "(s) for " + mainPurchase.getTotalPrice());
                    builder.show();
                    builder.setCancelable(true);

                    // set date for mainPurchase
                    mainPurchase.setPurchaseDate(new Date());

                    // add order to history list
                    historyManager.addPurchase(mainPurchase);

                    // update quantity of product
                    int productPos = productManager.getProductPos(mainPurchase.getName());
                    productManager.getAllProducts().get(productPos).setQuantity(productManager.getAllProducts().get(productPos).getQuantity() - mainPurchase.getQuantity());

                    // redisplay list view using base adapter
                    productAdapter.notifyDataSetChanged();

                    // reset all values
                    resetValues();
                }
                break;
        }
    }
}