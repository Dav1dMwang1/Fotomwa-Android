package com.example.dijonkariz.fotomwa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dijonkariz.fotomwa.R;
import com.example.dijonkariz.fotomwa.model.Message;

import java.util.List;

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
        holder.notification_title.setText(messages.get(position).getTitle());
        holder.notification_desc.setText(messages.get(position).getDescription());
        holder.notification_time.setText(messages.get(position).getTime());
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
