package com.example.dijonkariz.fotomwa.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dijonkariz.fotomwa.R;
import com.example.dijonkariz.fotomwa.fragments.HomeFragment;
import com.example.dijonkariz.fotomwa.objects.Order;

import java.util.ArrayList;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.MyViewHolder> {
//    private Context context;
    private ArrayList<Order> orderList;

    public OrdersAdapter(ArrayList<Order> orderList) {
//        this.context = context;
        this.orderList = orderList;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView order_type, order_size;
        ImageView view_icon;

        MyViewHolder(@NonNull View itemView) {
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
//        Order order = orderList.get(i);
        myViewHolder.order_type.setText(orderList.get(i).getOrder_type());
        myViewHolder.order_size.setText(orderList.get(i).getOrder_size());
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

}
