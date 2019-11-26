package com.example.dijonkariz.fotomwa.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.dijonkariz.fotomwa.R;
import com.example.dijonkariz.fotomwa.fragments.PhotosFragment;
import com.example.dijonkariz.fotomwa.interfaces.GalleryItemClickListener;
import com.example.dijonkariz.fotomwa.model.ImageModel;

import java.util.ArrayList;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {
    private final GalleryItemClickListener galleryItemClickListener;
    private ArrayList<ImageModel> images;

    public GalleryAdapter(ArrayList<ImageModel> images, GalleryItemClickListener galleryItemClickListener) {
        this.galleryItemClickListener = galleryItemClickListener;
        this.images = images;
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_thumbnail, parent, false);
        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {
        final ImageModel image = images.get(position);
        Glide.with(holder.thumbnail.getContext()).load(image.getUrl()).thumbnail(0.5f)
                .transition(withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.thumbnail);

        // Set transition name same as the Image name
        ViewCompat.setTransitionName(holder.thumbnail, image.getName());

        holder.thumbnail.setOnClickListener(v -> galleryItemClickListener.onGalleryItemClickListener(holder.getAdapterPosition(), image, holder.thumbnail));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    static class GalleryViewHolder extends RecyclerView.ViewHolder {
        private ImageView thumbnail;

        GalleryViewHolder(@NonNull View itemView) {
            super(itemView);
            thumbnail = itemView.findViewById(R.id.galley_thumbnail);
        }
    }
}
