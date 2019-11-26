package com.example.dijonkariz.fotomwa.fragments;

import android.os.Bundle;
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
import androidx.transition.Fade;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dijonkariz.fotomwa.R;
import com.example.dijonkariz.fotomwa.adapter.OrdersAdapter;
import com.example.dijonkariz.fotomwa.fragments.orders.IndividualOrderFragment;
import com.example.dijonkariz.fotomwa.model.Order;
import com.example.dijonkariz.fotomwa.other.DetailsTransition;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private static final String TAG = HomeFragment.class.getSimpleName();
    private List<Order> orderList = new ArrayList<>();
    private ProgressBar currentOrdersProgressBar, recentOrdersProgressBar;
    private RecyclerView recyclerViewCurrentOrders, recyclerViewRecentOrders;
    private View.OnClickListener onOrderItemClickListener = v -> {
        RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
        int position = viewHolder.getAdapterPosition();
        Order orderObject = orderList.get(position);
        Toast.makeText(getActivity(), "Goes to the Individual View for a Particular Order", Toast.LENGTH_SHORT).show();
        Fragment fragment = new IndividualOrderFragment(orderObject);
//        Animation
        fragment.setSharedElementEnterTransition(new DetailsTransition());
        fragment.setEnterTransition(new Fade());
        setExitTransition(new Fade());
        fragment.setSharedElementReturnTransition(new DetailsTransition());

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addSharedElement(viewHolder.itemView, String.valueOf(R.string.transition_string))
                .replace(R.id.frame, fragment, TAG)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    };
    private OrdersAdapter ordersAdapter;

    public HomeFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ordersAdapter = new OrdersAdapter(getContext(), orderList);
        recyclerViewCurrentOrders = view.findViewById(R.id.current_orders_list);
        recyclerViewRecentOrders = view.findViewById(R.id.recent_orders_list);
        currentOrdersProgressBar = view.findViewById(R.id.current_orders_progressBar);
        recentOrdersProgressBar = view.findViewById(R.id.recent_orders_progressBar);

        initRecyclerView(recyclerViewCurrentOrders);
        initRecyclerView(recyclerViewRecentOrders);

        prepareOrders();

        return view;
    }

    //    Initialize RecyclerView
    private void initRecyclerView(RecyclerView recyclerView) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setMotionEventSplittingEnabled(false);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(ordersAdapter);
        ordersAdapter.setOnItemClickListener(onOrderItemClickListener);
    }

    /**
     * Adding few orders for testing
     */
    private void prepareOrders() {
        Order a = new Order("Nicollet Njora", "Customer");
        orderList.add(a);
        Order b = new Order("David Kariuki", "Administrator");
        orderList.add(b);
        Order c = new Order("Nannet Wanjiku", "Customer");
        orderList.add(c);
        Order d = new Order("John Mwangi", "Customer");
        orderList.add(d);
        ordersAdapter.notifyDataSetChanged();
    }
}
