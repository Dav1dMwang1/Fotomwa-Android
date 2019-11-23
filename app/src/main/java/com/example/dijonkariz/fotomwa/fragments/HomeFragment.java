package com.example.dijonkariz.fotomwa.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.dijonkariz.fotomwa.R;
import com.example.dijonkariz.fotomwa.adapter.OrdersAdapter;
import com.example.dijonkariz.fotomwa.objects.Order;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private static final String TAG = HomeFragment.class.getSimpleName();
    private OrdersAdapter ordersAdapter;
    private ProgressBar currentOrdersProgressBar, recentOrdersProgressBar;
    private RecyclerView recyclerViewCurrentOrders, recyclerViewRecentOrders;
    private ArrayList<Order> orderList;

    public HomeFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerViewCurrentOrders = view.findViewById(R.id.current_orders_list);
        recyclerViewRecentOrders = view.findViewById(R.id.recent_orders_list);
        currentOrdersProgressBar = view.findViewById(R.id.current_orders_progressBar);
        recentOrdersProgressBar = view.findViewById(R.id.recent_orders_progressBar);

        initRecyclerView(recyclerViewCurrentOrders);
        initRecyclerView(recyclerViewRecentOrders);

        return view;
    }

    //    Initialize RecyclerView
    private void initRecyclerView(RecyclerView recyclerView) {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setMotionEventSplittingEnabled(false);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        prepareOrders();

        recyclerView.setAdapter(ordersAdapter);
    }

    /**
     * Adding few orders for testing
     */
    private void prepareOrders() {
        orderList = new ArrayList<Order>();

        Order a = new Order("Nicollet Njora", "Customer");
        orderList.add(a);

        Order b = new Order("David Kariuki", "Administrator");
        orderList.add(b);

        Order c = new Order("Nannet Wanjiku", "Customer");
        orderList.add(c);

        Order d = new Order("John Mwangi", "Customer");
        orderList.add(d);
        orderList.add(d);
        orderList.add(d);
        orderList.add(d);
        orderList.add(d);
        orderList.add(d);

        ordersAdapter = new OrdersAdapter(orderList);
        ordersAdapter.notifyDataSetChanged();
    }
}
