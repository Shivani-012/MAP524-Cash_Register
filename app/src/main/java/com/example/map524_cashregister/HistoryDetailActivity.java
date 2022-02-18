package com.example.map524_cashregister;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HistoryDetailActivity extends AppCompatActivity {

    // declare variables for date, product, quantity and total text views
    TextView dateText;
    TextView productText;
    TextView qtyText;
    TextView totalText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);

        // initialize date text field and set text from intent's extras
        dateText = findViewById(R.id.detailDate);
        dateText.setText(getIntent().getExtras().getString("date"));

        // initialize product name text field and set text from intent's extras
        productText = findViewById(R.id.detailProduct);
        productText.setText(getIntent().getExtras().getString("name"));

        // initialize quantity text field and set text from intent's extras
        qtyText = findViewById(R.id.detailQty);
        qtyText.setText(getString(R.string.quantityDetailLabel) + getIntent().getExtras().getString("qty"));

        // initialize total price text field and set text from intent's extras
        totalText = findViewById(R.id.detailPrice);
        totalText.setText(getString(R.string.totalDetailLabel) + getIntent().getExtras().getString("total"));
    }
}