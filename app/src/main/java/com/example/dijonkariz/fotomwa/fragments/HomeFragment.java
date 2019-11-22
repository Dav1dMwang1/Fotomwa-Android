package com.example.dijonkariz.fotomwa.fragments;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dijonkariz.fotomwa.R;
import com.example.dijonkariz.fotomwa.activity.HomePageActivity;
import com.example.dijonkariz.fotomwa.activity.LandingPageActivity;
import com.example.dijonkariz.fotomwa.adapter.OrdersAdapter;
import com.example.dijonkariz.fotomwa.objects.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment {
    private static final String TAG = HomeFragment.class.getSimpleName();
    private OrdersAdapter ordersAdapter;
    private RecyclerView recyclerView;
    private List<Order> orderList;

    public HomeFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        recyclerView = Objects.requireNonNull(getView()).findViewById(R.id.current_orders_list);
        orderList = new ArrayList<>();
        ordersAdapter = new OrdersAdapter(getActivity(), orderList);

        initRecyclerView();

        prepareOrders();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Objects.requireNonNull(getActivity()).setTitle("Home");
    }

//    Initialize RecyclerView
    private void initRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setMotionEventSplittingEnabled(false);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        
        recyclerView.setAdapter(ordersAdapter);
    }

    /**
     * Adding few albums for testing
     */
    private void prepareOrders() {
        Order a = new Order("David Kariuki", "Customer");
        orderList.add(a);

        Order b = new Order("David Kariuki", "Customer");
        orderList.add(b);

        Order c = new Order("David Kariuki", "Customer");
        orderList.add(c);

        Order d = new Order("David Kariuki", "Customer");
        orderList.add(d);
        orderList.add(d);
        orderList.add(d);
        orderList.add(d);
        orderList.add(d);
        orderList.add(d);
        orderList.add(d);
        orderList.add(d);

        ordersAdapter.notifyDataSetChanged();
    }
}
