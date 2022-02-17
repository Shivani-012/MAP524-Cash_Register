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

    HistoryManager history_list;
    HistoryRecyclerAdapter historyAdapter;



    RecyclerView history_table;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        history_list = ((MyApp)getApplication()).historyList;

        history_table = findViewById(R.id.recyclerViewHistory);
        historyAdapter = new HistoryRecyclerAdapter(history_list.getAllPurchases(), this, this);
        history_table.setAdapter(historyAdapter);

        history_table.setLayoutManager((new LinearLayoutManager(this)));

    }

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