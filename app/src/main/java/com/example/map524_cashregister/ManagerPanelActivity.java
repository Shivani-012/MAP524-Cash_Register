package com.example.map524_cashregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManagerPanelActivity extends AppCompatActivity
        implements View.OnClickListener{

    Button history_btn;
    Button restock_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_panel);

        history_btn = findViewById(R.id.historyBtn);
        history_btn.setOnClickListener(this);

        restock_btn = findViewById(R.id.restockBtn);
        restock_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch(id) {
            case R.id.historyBtn:
                Intent historyIntent = new Intent(this, HistoryActivity.class);
                startActivity(historyIntent);
                break;
            case R.id.restockBtn:
                Intent restockIntent = new Intent(this, RestockActivity.class);
                startActivity(restockIntent);
                break;
        }
    }
}