package com.example.dijonkariz.fotomwa.fragments.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.transition.Fade;

import com.example.dijonkariz.fotomwa.R;
import com.example.dijonkariz.fotomwa.adapter.OrdersAdapter;
import com.example.dijonkariz.fotomwa.interfaces.OnBackPressed;
import com.example.dijonkariz.fotomwa.model.Order;
import com.example.dijonkariz.fotomwa.other.DetailsTransition;

import java.util.ArrayList;
import java.util.List;

public class ViewOrdersFragment extends Fragment implements OnBackPressed {
    private static final String TAG = ViewOrdersFragment.class.getSimpleName();
    private static String CURRENT_TAG = TAG;
    private List<Order> orders = new ArrayList<>();
    private RecyclerView recyclerView;
    private View.OnClickListener onItemClickListener = v -> {
        RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
        int position = viewHolder.getAdapterPosition();
        Order order = orders.get(position);
        Toast.makeText(getActivity(), "Goes to the Order View for one Particular Order" , Toast.LENGTH_SHORT).show();
        Fragment fragment = new IndividualOrderFragment(order);
//        Animation
        fragment.setSharedElementEnterTransition(new DetailsTransition());
        fragment.setEnterTransition(new Fade());
        setExitTransition(new Fade());
        fragment.setSharedElementReturnTransition(new DetailsTransition());

        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.addSharedElement(viewHolder.itemView, String.valueOf(R.string.transition_string))
                .replace(R.id.frame, fragment, CURRENT_TAG)
                .addToBackStack(null)
                .commitAllowingStateLoss();
    };
    private OrdersAdapter ordersAdapter;

    public ViewOrdersFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_orders, container, false);

        recyclerView = view.findViewById(R.id.all_orders_list);

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

    @Override
    public void onBackPressed() {
        getActivity().getSupportFragmentManager().popBackStack();
    }
}
