package com.example.dijonkariz.fotomwa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dijonkariz.fotomwa.R;
import com.example.dijonkariz.fotomwa.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.MyViewHolder> {
    private List<Order> orderList;
    private View.OnClickListener onOrderItemClickListener;

    public OrdersAdapter(Context context, List<Order> orderList) {
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_list_instance, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {
        myViewHolder.order_type.setText(orderList.get(i).getOrder_type());
        myViewHolder.order_size.setText(orderList.get(i).getOrder_size());
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView order_type, order_size;
        ImageView view_icon;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setTag(this);
            order_type = itemView.findViewById(R.id.order_type);
            order_size = itemView.findViewById(R.id.order_size);
            view_icon = itemView.findViewById(R.id.order_list_view_icon);
            itemView.setOnClickListener(onOrderItemClickListener);
        }
    }

    public void setOnItemClickListener(View.OnClickListener clickListener){
        onOrderItemClickListener = clickListener;
    }

}
