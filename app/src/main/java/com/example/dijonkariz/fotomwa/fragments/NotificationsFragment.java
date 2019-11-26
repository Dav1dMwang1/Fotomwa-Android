package com.example.dijonkariz.fotomwa.fragments;

// TODO: add expanded notification for more details

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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dijonkariz.fotomwa.R;
import com.example.dijonkariz.fotomwa.adapter.MessagesAdapter;
import com.example.dijonkariz.fotomwa.model.Message;
import com.example.dijonkariz.fotomwa.other.DetailsTransition;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {
    private static final String TAG = NotificationsFragment.class.getSimpleName();
    private List<Message> messages = new ArrayList<>();
    private RecyclerView recyclerView;
    private View.OnClickListener onItemClickListener = v -> {
        RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
        int position = viewHolder.getAdapterPosition();
        Message notificationMessage = messages.get(position);
        Toast.makeText(getActivity(), "Does the Expand Notification Message to show more Content.", Toast.LENGTH_SHORT).show();
//        Fragment fragment = new IndividualOrderFragment(notificationMessage);
////        Animation
//        fragment.setSharedElementEnterTransition(new DetailsTransition());
//        fragment.setEnterTransition(new Fade());
//        setExitTransition(new Fade());
//        fragment.setSharedElementReturnTransition(new DetailsTransition());
//
//        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
//        fragmentTransaction.addSharedElement(viewHolder.itemView, String.valueOf(R.string.transition_string))
//                .replace(R.id.frame, fragment, TAG)
//                .addToBackStack(null)
//                .commitAllowingStateLoss();
    };
    private MessagesAdapter messagesAdapter;

    public NotificationsFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        recyclerView = view.findViewById(R.id.notifications_recycler_view);

        messagesAdapter = new MessagesAdapter(getContext(), messages);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(messagesAdapter);
        messagesAdapter.setOnItemClickListener(onItemClickListener);
//        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//            @Override
//            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
//                return false;
//            }
//
//            @Override
//            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
//
//            }
//
//            @Override
//            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//            }
//        });
        
        prepareMessagesData();

        return view;
    }

    private void prepareMessagesData() {
        Message message = new Message("Customer Actions", "Customer has created a new Order", "13:01 p.m");
        messages.add(message);
        messages.add(message);
        messages.add(message);
        messages.add(message);
        messages.add(message);
        messages.add(message);
        messages.add(message);
        messages.add(message);
        messagesAdapter.notifyDataSetChanged();
    }
}
