package com.example.map524_cashregister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

public class HistoryActivity extends AppCompatActivity implements HistoryRecyclerAdapter.OnSelectListener {

    // declare history manager holding purchases
    HistoryManager history_list;

    // create adaptor for history of purchases
    HistoryRecyclerAdapter historyAdapter;

    // create recycler view to hold history purchase items
    RecyclerView history_table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        // initialize history manager from MyApp
        history_list = ((MyApp)getApplication()).historyList;

        // initialize history table
        history_table = findViewById(R.id.recyclerViewHistory);

        // create history adapter and set table
        historyAdapter = new HistoryRecyclerAdapter(history_list.getAllPurchases(), this, this);
        history_table.setAdapter(historyAdapter);

        // set layout to lay view items vertically
        history_table.setLayoutManager((new LinearLayoutManager(this)));

    }

    // Method created to detect when a purchase item is clicked in the table
    @Override
    public void OnPurchaseSelect(int position) {
        // get selected purchase
        Purchase selectedPurchase = history_list.getAllPurchases().get(position);

        // create intent for history detail activity
        Intent historyDetailIntent = new Intent(this, HistoryDetailActivity.class);

        // set extras for content that will fill the history detail views
        historyDetailIntent.putExtra("name", selectedPurchase.getName());
        historyDetailIntent.putExtra("date", selectedPurchase.getPurchaseDate());
        historyDetailIntent.putExtra("qty", String.valueOf(selectedPurchase.getQuantity()));
        historyDetailIntent.putExtra("total", selectedPurchase.getTotalPrice());

        // open history detail activity
        startActivity(historyDetailIntent);
    }
}