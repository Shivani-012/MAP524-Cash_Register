package com.example.map524_cashregister;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HistoryDetailActivity extends AppCompatActivity {

    TextView dateText;
    TextView productText;
    TextView qtyText;
    TextView totalText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_detail);

        dateText = findViewById(R.id.detailDate);
        dateText.setText(getIntent().getExtras().getString("date"));

        productText = findViewById(R.id.detailProduct);
        productText.setText(getIntent().getExtras().getString("name"));

        qtyText = findViewById(R.id.detailQty);
        qtyText.setText("Quantity: " + getIntent().getExtras().getString("qty"));

        totalText = findViewById(R.id.detailPrice);
        totalText.setText("Total: " + getIntent().getExtras().getString("total"));
    }
}