package com.example.dijonkariz.fotomwa.fragments.orders;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dijonkariz.fotomwa.R;
import com.example.dijonkariz.fotomwa.model.Order;

public class IndividualOrderFragment extends Fragment {
    private static final String TAG = ViewOrdersFragment.class.getSimpleName();
    private Order order;

    public IndividualOrderFragment() {}

    public IndividualOrderFragment(Order order) {
        this.order = order;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.order_view, container, false);

        return view;
    }


}
