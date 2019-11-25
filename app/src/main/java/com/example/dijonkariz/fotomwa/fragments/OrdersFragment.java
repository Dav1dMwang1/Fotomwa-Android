package com.example.dijonkariz.fotomwa.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dijonkariz.fotomwa.R;
import com.example.dijonkariz.fotomwa.adapter.OrdersAdapter;
import com.example.dijonkariz.fotomwa.model.Order;

import java.util.ArrayList;
import java.util.List;

public class OrdersFragment extends Fragment {
    private static final String TAG = OrdersFragment.class.getSimpleName();
    private List<Order> orders = new ArrayList<>();
    private RecyclerView recyclerView;
    private View.OnClickListener onItemClickListener = v -> {
        RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
        int position = viewHolder.getAdapterPosition();
        Order order = orders.get(position);
        Toast.makeText(getActivity(), "You Clicked: " + order.getOrder_type(), Toast.LENGTH_SHORT).show();
    };
    private OrdersAdapter ordersAdapter;

    public OrdersFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders, container, false);

        ordersAdapter = new OrdersAdapter(getContext(), orders);


        return view;
    }
}
