package com.example.dijonkariz.fotomwa.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dijonkariz.fotomwa.R;
import com.example.dijonkariz.fotomwa.objects.Order;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.MyViewHolder> {
    private Context context;
    private List<Order> orderList;

    public OrdersAdapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView order_type, order_size;
        public ImageView view_icon;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            order_type = itemView.findViewById(R.id.order_type);
            order_size = itemView.findViewById(R.id.order_size);
            view_icon = itemView.findViewById(R.id.order_list_view_icon);
        }
    }

    @NonNull
    @Override
    public OrdersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.order_list_instance, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final OrdersAdapter.MyViewHolder myViewHolder, int i) {
        Order order = orderList.get(i);
        myViewHolder.order_type.setText(order.getOrder_type());
        myViewHolder.order_size.setText(order.getOrder_size());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
