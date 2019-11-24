package com.example.dijonkariz.fotomwa.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.dijonkariz.fotomwa.R;
import com.example.dijonkariz.fotomwa.helper.CircleTransform;
import com.example.dijonkariz.fotomwa.helper.FlipAnimator;
import com.example.dijonkariz.fotomwa.model.Message;

import java.util.ArrayList;
import java.util.List;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MyViewHolder> {
    private List<Message> messages;
    private View.OnClickListener onItemClickListener;

    public MessagesAdapter(Context mContext, List<Message> messages) {
        this.messages = messages;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.message_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Message message = messages.get(position);
        holder.notification_title.setText(message.getTitle());
        holder.notification_desc.setText(message.getDescription());
        holder.notification_time.setText(message.getTime());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView notification_title, notification_desc, notification_time;


        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setTag(this);
            notification_title = itemView.findViewById(R.id.notification_title);
            notification_desc = itemView.findViewById(R.id.notification_description);
            notification_time = itemView.findViewById(R.id.notification_time);
            itemView.setOnClickListener(onItemClickListener);
        }
    }

    public void setOnItemClickListener(View.OnClickListener clickListener){
        onItemClickListener = clickListener;
    }
}
