package com.example.dijonkariz.fotomwa.fragments.orders;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.dijonkariz.fotomwa.R;
import com.example.dijonkariz.fotomwa.adapter.OrdersAdapter;
import com.example.dijonkariz.fotomwa.model.Order;

import java.util.ArrayList;
import java.util.List;

import me.saket.inboxrecyclerview.InboxRecyclerView;
import me.saket.inboxrecyclerview.dimming.TintPainter;
import me.saket.inboxrecyclerview.page.ExpandablePageLayout;

public class ViewOrdersFragment extends Fragment {
    private static final String TAG = ViewOrdersFragment.class.getSimpleName();
    private static String CURRENT_TAG = TAG;
    private List<Order> orders = new ArrayList<>();
    private RecyclerView recyclerView;
    private InboxRecyclerView testRecycler;
    private ExpandablePageLayout testExpansion;
    private View.OnClickListener onItemClickListener = v -> {
        RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
        int position = viewHolder.getAdapterPosition();
        Order order = orders.get(position);
        Toast.makeText(getActivity(), "Goes to the Order View for one Particular Order" , Toast.LENGTH_SHORT).show();
        Fragment fragment = new IndividualOrderFragment(order);
//        getActivity().getSupportFragmentManager().beginTransaction()
//                .replace(R.id.frame, fragment)
//                .addToBackStack(null)
//                .commit();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out);
        fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
        fragmentTransaction.commitAllowingStateLoss();
    };
    private OrdersAdapter ordersAdapter;

    public ViewOrdersFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_orders, container, false);

        recyclerView = view.findViewById(R.id.all_orders_list);
        testRecycler = view.findViewById(R.id.test_recycler);
        testExpansion = view.findViewById(R.id.test_expansion);
        testRecycler.setExpandablePage(testExpansion);
        testRecycler.setTintPainter(TintPainter.uncoveredArea(Color.WHITE, 0.65f));
        testRecycler.setOnClickListener(v -> {
            testRecycler.expandItem(R.id.test_expansion);
        });

        ordersAdapter = new OrdersAdapter(getContext(), orders);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(ordersAdapter);
        ordersAdapter.setOnItemClickListener(onItemClickListener);

        prepareOrders();

        return view;
    }

    /**
     * Adding few orders for testing
     */
    private void prepareOrders() {
        Order a = new Order("Nicollet Njora", "Customer");
        Order b = new Order("David Kariuki", "Administrator");
        Order c = new Order("Nannet Wanjiku", "Customer");
        Order d = new Order("John Mwangi", "Customer");
        orders.add(a);
        orders.add(b);
        orders.add(c);
        orders.add(d);
        ordersAdapter.notifyDataSetChanged();
    }
}
