package com.example.map524_cashregister;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryRecyclerAdapter.HistoryViewHolder> {

    // declare array list of products, context and special OnSelectListener object
    private ArrayList<Purchase> listOfPurchases;
    private Context context;
    private OnSelectListener myOnSelectListener;

    // Overloaded constructor that sets listOfPurchases, context and onSelectListener
    public HistoryRecyclerAdapter(ArrayList<Purchase> listOfPurchases, Context context, OnSelectListener onSelectListener) {
        this.listOfPurchases = listOfPurchases;
        this.context = context;
        this.myOnSelectListener = onSelectListener;
    }

    // create method for a history view holder
    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // use inflater to set view to existing row
        View view = LayoutInflater.from(context).inflate(R.layout.base_adapter_product_row, parent, false);
        // return created view holder
        return new HistoryViewHolder(view, myOnSelectListener);
    }

    // method that binds values of a purchase to view holder
    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.productText.setText(listOfPurchases.get(position).getName());
        holder.qtyText.setText(String.valueOf(listOfPurchases.get(position).getQuantity()));
        holder.totalText.setText(listOfPurchases.get(position).getTotalPrice());
    }

    // method that returns the size of array list of purchases
    @Override
    public int getItemCount() {
        return listOfPurchases.size();
    }

    // History View Holder Class
    public static class HistoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // declare text views for each view field
        TextView productText;
        TextView qtyText;
        TextView totalText;

        OnSelectListener myOnSelectListener; // declare onSelectListener object

        // overloaded constructor
        public HistoryViewHolder(@NonNull View itemView, OnSelectListener onSelectListener) {
            super(itemView);

            // initialize each text view
            productText = itemView.findViewById(R.id.row_product);
            qtyText = itemView.findViewById(R.id.row_qty);
            totalText = itemView.findViewById(R.id.row_price);

            this.myOnSelectListener = onSelectListener; // set onSelectListener object

            itemView.setOnClickListener(this); // listen to clicked for this holder
        }

        // when view holder is clicked, call OnPurchaseSelect() passing the position of the purchase in the array list
        @Override
        public void onClick(View view) {
            myOnSelectListener.OnPurchaseSelect(getAdapterPosition());
        }
    }

    // special interface to help detect view holder clicks
    public interface OnSelectListener{
        void OnPurchaseSelect(int position);
    }

}
