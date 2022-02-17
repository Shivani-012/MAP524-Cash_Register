package com.example.map524_cashregister;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class RestockActivity extends AppCompatActivity
        implements View.OnClickListener{

    EditText new_qty_text;

    Button ok_btn;
    Button cancel_btn;

    ListView product_list;

    ProductManager productManager;
    ArrayList<Product> productList;

    int productPos;

    Product selectedProduct;

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restock);

        builder = new AlertDialog.Builder(this);

        productManager = ((MyApp)getApplication()).productList;
        productList = productManager.getAllProducts();

        selectedProduct = new Product();

        new_qty_text = findViewById(R.id.qtyRestockText);

        ok_btn = findViewById(R.id.okBtn);
        ok_btn.setOnClickListener(this);
        cancel_btn = findViewById(R.id.cancelBtn);
        cancel_btn.setOnClickListener(this);

        product_list = findViewById(R.id.productRestockList);
        ProductBaseAdapter productAdapter = new ProductBaseAdapter(productList, this);
        product_list.setAdapter(productAdapter);
        product_list.setOnItemClickListener(messageClickedHandler);
    }

    // Create a message handling object as an anonymous class.
    private AdapterView.OnItemClickListener messageClickedHandler = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView parent, View v, int position, long id) {
            Log.d("test", "item clicked; id: " + id + " pos: " + position);
            selectedProduct = productList.get(position);

            for(int a = 0; a < parent.getChildCount(); a++) {
                parent.getChildAt(a).setBackgroundColor(getResources().getColor(R.color.cardview_light_background, null));
            }
            v.setBackgroundColor(getResources().getColor(R.color.grey, null));
        }
    };

    public boolean validate(){
        if(selectedProduct.getName()==null){
            Toast.makeText(this,"Select a Product!",Toast.LENGTH_LONG).show();
            return false;
        }
        else if(String.valueOf(new_qty_text.getText())==null || String.valueOf(new_qty_text.getText()).equals("") || Integer.parseInt(String.valueOf(new_qty_text.getText())) < 1){
            Toast.makeText(this,"A valid quantity is required!",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch(id){
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
                    ProductBaseAdapter productAdapter = new ProductBaseAdapter(productList, this);
                    product_list.setAdapter(productAdapter);

                    // display alert that qty update was successful
                    builder.setTitle("Update Successful");
                    builder.setMessage("You have added " + addQty + " " + selectedProduct.getName() +"(s) for an updated total quantity of " + selectedProduct.getQuantity());
                    builder.show();
                    builder.setCancelable(true);
                }

                break;
            case R.id.cancelBtn:
                Intent managerIntent = new Intent(this, ManagerPanelActivity.class);
                startActivity(managerIntent);
                break;
        }

    }
}