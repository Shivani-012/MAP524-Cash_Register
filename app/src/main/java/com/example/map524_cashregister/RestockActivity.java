package com.example.map524_cashregister;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class RestockActivity extends AppCompatActivity {

    EditText new_qty_text;

    Button ok_btn;
    Button cancel_btn;

    ListView product_list;
    ArrayList productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restock);


    }
}