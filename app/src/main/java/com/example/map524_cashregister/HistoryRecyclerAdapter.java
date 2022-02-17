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

    private ArrayList<Purchase> listOfPurchases;
    private Context context;
    private OnSelectListener myOnSelectListener;

    public HistoryRecyclerAdapter(ArrayList<Purchase> listOfPurchases, Context context, OnSelectListener onSelectListener) {
        this.listOfPurchases = listOfPurchases;
        this.context = context;
        this.myOnSelectListener = onSelectListener;
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.base_adapter_product_row, parent, false);
        return new HistoryViewHolder(view, myOnSelectListener);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        holder.productText.setText(listOfPurchases.get(position).getName());
        holder.qtyText.setText(String.valueOf(listOfPurchases.get(position).getQuantity()));
        holder.totalText.setText(listOfPurchases.get(position).getTotalPrice());
    }

    @Override
    public int getItemCount() {
        return listOfPurchases.size();
    }

    public static class HistoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView productText;
        TextView qtyText;
        TextView totalText;

        OnSelectListener myOnSelectListener;

        public HistoryViewHolder(@NonNull View itemView, OnSelectListener onSelectListener) {
            super(itemView);
            productText = itemView.findViewById(R.id.row_product);
            qtyText = itemView.findViewById(R.id.row_qty);
            totalText = itemView.findViewById(R.id.row_price);

            this.myOnSelectListener = onSelectListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            myOnSelectListener.OnPurchaseSelect(getAdapterPosition());
        }
    }

    public interface OnSelectListener{
        void OnPurchaseSelect(int position);
    }

}
