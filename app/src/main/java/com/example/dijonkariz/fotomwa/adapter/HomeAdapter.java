package com.example.dijonkariz.fotomwa.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dijonkariz.fotomwa.R;
import com.example.dijonkariz.fotomwa.objects.User;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    private Context context;
    private List<User> userList;

    public HomeAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView user_name, user_role;
        public ImageView thumbnail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            user_name = itemView.findViewById(R.id.user_card_name);
            user_role = itemView.findViewById(R.id.user_role);
            thumbnail = itemView.findViewById(R.id.thumbnail);
        }
    }

    @NonNull
    @Override
    public HomeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.user_card, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeAdapter.MyViewHolder myViewHolder, int i) {
        User user= userList.get(i);
        myViewHolder.user_name.setText(user.getUser_name());
        myViewHolder.user_role.setText(user.getUser_role());

        // loading album cover using Glide library
        Glide.with(context).load(user.getThumbnail()).into(myViewHolder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
