package com.example.map524_cashregister;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ??
        product_text = findViewById(R.id.productText);
        product_text.setOnClickListener(this);
        qty_text = findViewById(R.id.quantityText);
        total_text = findViewById(R.id.totalText);


        manager_btn = findViewById(R.id.managerBtn);
        manager_btn.setOnClickListener(this);
        buy_btn = findViewById(R.id.buyBtn);
        buy_btn.setOnClickListener(this);

        qty_picker = findViewById(R.id.numPicker);
        qty_picker.setOnClickListener(this);

        if (qty_picker != null) {
            qty_picker.setMinValue(0);
            qty_picker.setMaxValue(100);
            qty_picker.setWrapSelectorWheel(true);
            qty_picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
                @Override
                public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                    Log.e("test", "e changed to" + newVal);

                    // update quantity text
                    // update history object
                    // update total

                    //String text = "Changed from " + oldVal + " to " + newVal;
                    //Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                }
            });
        }


            product_list = findViewById(R.id.productList);
        //product_list.setOnItemClickListener(this);

    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch(id){
            case R.id.managerBtn:
                // open manager activity
                Log.e("test", "manager clicked");
                break;
            case R.id.buyBtn:
                // validate that
                //      1) product is selected
                //      2) quantity is selected
                //      3) selected quantity is not over current available quantity

                // display pop-up that purchase was successful
                // add order to history list
                // set to a new history/purchase
                Log.e("test", "buy clicked");
                break;
            case R.id.numPicker:
                // update quantity text view
                Log.e("test", "number picked");
                break;
            case R.id.productList:
                // update product text view
                Log.e("test", "product selected");
                break;
        }

    }
}