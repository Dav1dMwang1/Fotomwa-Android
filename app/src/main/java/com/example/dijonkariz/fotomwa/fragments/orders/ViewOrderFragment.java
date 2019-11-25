package com.example.dijonkariz.fotomwa.fragments.orders;

import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dijonkariz.fotomwa.adapter.OrdersAdapter;
import com.example.dijonkariz.fotomwa.fragments.NotificationsFragment;
import com.example.dijonkariz.fotomwa.model.Order;

import java.util.ArrayList;
import java.util.List;

public class ViewOrderFragment extends Fragment {
    private static final String TAG = NotificationsFragment.class.getSimpleName();
    private List<Order> orders = new ArrayList<>();
    private RecyclerView recyclerView;
    private View.OnClickListener onItemClickListener = v -> {
        RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
        int position = viewHolder.getAdapterPosition();
        Order order = orders.get(position);
        Toast.makeText(getActivity(), "You Clicked: " + order.getOrder_type(), Toast.LENGTH_SHORT).show();
    };
    private OrdersAdapter ordersAdapter;

    public ViewOrderFragment() {}
}
