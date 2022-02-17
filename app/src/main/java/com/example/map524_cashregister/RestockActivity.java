package com.example.map524_cashregister;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class RestockActivity extends AppCompatActivity
        implements View.OnClickListener{

    // declare activity view elements
    EditText new_qty_text;
    Button ok_btn;
    Button cancel_btn;
    ListView product_list;

    // declare objects that will hold product data
    ProductManager productManager;
    ArrayList<Product> productList;
    Product selectedProduct;

    // declare builder for alert dialog box
    AlertDialog.Builder builder;

    // declare base adapter for products
    ProductBaseAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restock);

        // initialize builder for alert dialog box
        builder = new AlertDialog.Builder(this);

        // initialize variables holding product data from MyApp
        productManager = ((MyApp)getApplication()).productList;
        productList = productManager.getAllProducts();

        selectedProduct = new Product(); // initialize selectedProduct as a new Product

        // initialize quantity edit view
        new_qty_text = findViewById(R.id.qtyRestockText);

        // initialize ok and cancel buttons and set on click listener
        ok_btn = findViewById(R.id.okBtn);
        ok_btn.setOnClickListener(this);
        cancel_btn = findViewById(R.id.cancelBtn);
        cancel_btn.setOnClickListener(this);

        // initialize product list view
        product_list = findViewById(R.id.productRestockList);

        // declare and initialize base adapter for product list
        productAdapter = new ProductBaseAdapter(productList, this);
        product_list.setAdapter(productAdapter);

        // set on click listener for product list view items
        product_list.setOnItemClickListener(messageClickedHandler);
    }

    // Method that handles list view items clicks
    private AdapterView.OnItemClickListener messageClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            // set selectedProduct to the one represented in the item view
            selectedProduct = productList.get(position);

            // loop through list view elements and set the colour back to the default
            for(int a = 0; a < parent.getChildCount(); a++) {
                parent.getChildAt(a).setBackgroundColor(getResources().getColor(R.color.cardview_light_background, null));
            }
            // change the colour of the selected view item
            v.setBackgroundColor(getResources().getColor(R.color.grey, null));
        }
    };

    // method that validates if a product is selected and a valid quantity is entered
    public boolean validate(){
        // if product name is null, one was not selected..
        if(selectedProduct.getName()==null){
            // display error message and return false
            Toast.makeText(this,"Select a Product!",Toast.LENGTH_LONG).show();
            return false;
        }
        // else if quantity field is empty of if number is less than 1...
        else if(String.valueOf(new_qty_text.getText()) == null
                || String.valueOf(new_qty_text.getText()).equals("")
                || Integer.parseInt(String.valueOf(new_qty_text.getText())) < 1){
            // display error message and return false
            Toast.makeText(this,"A valid quantity is required!",Toast.LENGTH_LONG).show();
            return false;
        }
        return true; // else, is valid so return true
    }

    @Override
    public void onClick(View view) {

        int id = view.getId(); // get view id

        switch(id){
            // if ok button is clicked..
            case R.id.okBtn:

                // validate that:
                //      1) a product has been chosen
                //      2) the entered quantity is valid (more than 0)

                // if valid...
                if(validate()){
                    // get qty from edit text
                    int addQty = Integer.parseInt(String.valueOf(new_qty_text.getText()));

                    // update quantity of product
                    int productPos = productManager.getProductPos(selectedProduct.getName());
                    productManager.getAllProducts().get(productPos).setQuantity(productManager.getAllProducts().get(productPos).getQuantity() + addQty);
                    productAdapter.notifyDataSetChanged();

                    // clear the quantity edit text
                    new_qty_text.setText("");

                    // display alert that qty update was successful
                    builder.setTitle("Update Successful");
                    builder.setMessage("You have added " + addQty + " " + selectedProduct.getName() +"(s) for an updated total quantity of " + selectedProduct.getQuantity());
                    builder.show();
                    builder.setCancelable(true);
                }
                break;

                // if cancel button is clicked..
            case R.id.cancelBtn:
                // open previous manager panel activity
                Intent managerIntent = new Intent(this, ManagerPanelActivity.class);
                startActivity(managerIntent);
                break;
        }
    }
}