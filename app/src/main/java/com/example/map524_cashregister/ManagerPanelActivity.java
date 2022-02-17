package com.example.map524_cashregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManagerPanelActivity extends AppCompatActivity
        implements View.OnClickListener{

    // declare history and restock button objects
    Button history_btn;
    Button restock_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_panel);

        // initialize history button and set on click listener
        history_btn = findViewById(R.id.historyBtn);
        history_btn.setOnClickListener(this);

        // initialize restock button and set on click listener
        restock_btn = findViewById(R.id.restockBtn);
        restock_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        int id = view.getId(); // get id of view

        switch(id) {
            // if history button is clicked, open history activity
            case R.id.historyBtn:
                // open history activity
                Intent historyIntent = new Intent(this, HistoryActivity.class);
                startActivity(historyIntent);
                break;

                // if restock button is clicked, open restock activity
            case R.id.restockBtn:
                // open restock activity
                Intent restockIntent = new Intent(this, RestockActivity.class);
                startActivity(restockIntent);
                break;
        }
    }
}