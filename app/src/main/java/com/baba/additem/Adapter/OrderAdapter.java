package com.baba.additem.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.baba.additem.Model.Orders;
import com.baba.additem.R;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    List<Orders> ordersList;

    public OrderAdapter(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemName.setText(ordersList.get(position).getItemName());
        holder.totalPrice.setText(ordersList.get(position).getTotalAmount());
        holder.quantity.setText("Quantity : "+ordersList.get(position).getQuantity());
        holder.userName.setText(ordersList.get(position).getUserName());
        holder.userEmail.setText(ordersList.get(position).getUserEmail());
        holder.userNumber.setText(ordersList.get(position).getUserNumber());
        holder.userAddress.setText(ordersList.get(position).getUserAdd());
        holder.date.setText("Date : "+ordersList.get(position).getOrderDate());
        holder.time.setText("Time : "+ordersList.get(position).getOrderTime());
    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemName, totalPrice, quantity, userName, userNumber, userEmail, userAddress, date, time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            itemName = itemView.findViewById(R.id.itemName);
            totalPrice = itemView.findViewById(R.id.total_price);
            quantity = itemView.findViewById(R.id.quantity);
            userName = itemView.findViewById(R.id.user_name);
            userNumber = itemView.findViewById(R.id.user_number);
            userEmail = itemView.findViewById(R.id.user_email);
            userAddress = itemView.findViewById(R.id.user_add);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
        }
    }
}
